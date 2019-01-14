/**
 * Copyright 2018 Taucoin Core Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.taucoin.android.wallet.util;

import android.graphics.Bitmap;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.naturs.logger.Logger;
import com.mofei.tau.R;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.taucoin.android.wallet.MyApplication;
import io.taucoin.android.wallet.db.entity.KeyValue;
import io.taucoin.foundation.net.callback.LogicObserver;
import io.taucoin.foundation.net.exception.CodeException;
import io.taucoin.foundation.util.StringUtil;

public class UserUtil {

    private static String parseNickName(KeyValue keyValue) {
        String nickName = null;
        if(keyValue != null){
            nickName = keyValue.getNickName();
            if(StringUtil.isEmpty(nickName)){
                String address = keyValue.getAddress();
                if(StringUtil.isNotEmpty((address))){
                    int length = address.length();
                    nickName = length < 6 ? address : address.substring(length - 6);
                }
            }
        }
        return nickName;
    }

    public static void setNickName(TextView tvNick) {
        if(tvNick == null){
            return;
        }
        KeyValue keyValue = MyApplication.getKeyValue();
        if(keyValue == null){
            return;
        }
        String nickName = parseNickName(keyValue);
        tvNick.setText(nickName);
        Logger.d("UserUtil.setNickName=" + nickName);
    }

    public static void setBalance(TextView tvBalance) {
        if(tvBalance == null){
            return;
        }
        KeyValue keyValue = MyApplication.getKeyValue();
        if(keyValue == null){
            setBalance(tvBalance, 0L);
            return;
        }
        setBalance(tvBalance, keyValue.getBalance());
    }

    private static void setBalance(TextView tvBalance, long balance) {
        String balanceStr = MyApplication.getInstance().getResources().getString(R.string.common_balance);
        balanceStr = String.format(balanceStr, FmtMicrometer.fmtBalance(balance));
        tvBalance.setText(Html.fromHtml(balanceStr));
        Logger.d("UserUtil.setBalance=" + balanceStr);
    }

    public static void setAvatar(ImageView ivAvatar) {
        if(ivAvatar == null){
            return;
        }
        KeyValue keyValue = MyApplication.getKeyValue();
        if(keyValue != null){
            Observable.create((ObservableOnSubscribe<Bitmap>) emitter -> {
                if(StringUtil.isNotEmpty(keyValue.getHeaderImage())){
                    Bitmap bitmap = FileUtil.getFilesDirBitmap(keyValue.getHeaderImage());
                    if(bitmap != null){
                        emitter.onNext(bitmap);
                    }else{
                        emitter.onError(CodeException.getError());
                    }
                }else{
                    emitter.onError(CodeException.getError());
                }
            }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new LogicObserver<Bitmap>() {

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ivAvatar.setImageResource(R.mipmap.icon_avatar);
                    }

                    @Override
                    public void handleData(Bitmap bitmap) {
                        Logger.d("UserUtil.setAvatar=" + bitmap.getByteCount());
                        ivAvatar.setImageBitmap(bitmap);
                    }
                });
        }
    }

    public static boolean isImportKey() {
        KeyValue keyValue = MyApplication.getKeyValue();
        return  keyValue != null;
    }
}