package com.mofei.tau.transaction;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ly on 18-11-21
 *
 * @version 1.0
 * @description:
 */
@Entity
public class KeyValue {
    @Id
    private Long id;
    private String pubkey;
    private String privkey;
    private String address;
    private long utxo;
    private long reward;
    private long balance;
    @Generated(hash = 1410993070)
    public KeyValue(Long id, String pubkey, String privkey, String address,
            long utxo, long reward, long balance) {
        this.id = id;
        this.pubkey = pubkey;
        this.privkey = privkey;
        this.address = address;
        this.utxo = utxo;
        this.reward = reward;
        this.balance = balance;
    }
    @Generated(hash = 92014137)
    public KeyValue() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPubkey() {
        return this.pubkey;
    }
    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }
    public String getPrivkey() {
        return this.privkey;
    }
    public void setPrivkey(String privkey) {
        this.privkey = privkey;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public long getUtxo() {
        return this.utxo;
    }
    public void setUtxo(long utxo) {
        this.utxo = utxo;
    }
    public long getReward() {
        return this.reward;
    }
    public void setReward(long reward) {
        this.reward = reward;
    }
    public long getBalance() {
        return this.balance;
    }
    public void setBalance(long balance) {
        this.balance = balance;
    }
    
}
