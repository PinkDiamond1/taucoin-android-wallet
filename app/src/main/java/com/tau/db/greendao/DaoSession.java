package com.mofei.tau.db.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.mofei.tau.transaction.UTXORecord;
import com.mofei.tau.transaction.TransactionHistory;
import com.mofei.tau.db.greendao.User;
import com.mofei.tau.transaction.KeyStore;

import com.mofei.tau.db.greendao.UTXORecordDao;
import com.mofei.tau.db.greendao.TransactionHistoryDao;
import com.mofei.tau.db.greendao.UserDao;
import com.mofei.tau.db.greendao.KeyStoreDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig uTXORecordDaoConfig;
    private final DaoConfig transactionHistoryDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig keyStoreDaoConfig;

    private final UTXORecordDao uTXORecordDao;
    private final TransactionHistoryDao transactionHistoryDao;
    private final UserDao userDao;
    private final KeyStoreDao keyStoreDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        uTXORecordDaoConfig = daoConfigMap.get(UTXORecordDao.class).clone();
        uTXORecordDaoConfig.initIdentityScope(type);

        transactionHistoryDaoConfig = daoConfigMap.get(TransactionHistoryDao.class).clone();
        transactionHistoryDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        keyStoreDaoConfig = daoConfigMap.get(KeyStoreDao.class).clone();
        keyStoreDaoConfig.initIdentityScope(type);

        uTXORecordDao = new UTXORecordDao(uTXORecordDaoConfig, this);
        transactionHistoryDao = new TransactionHistoryDao(transactionHistoryDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        keyStoreDao = new KeyStoreDao(keyStoreDaoConfig, this);

        registerDao(UTXORecord.class, uTXORecordDao);
        registerDao(TransactionHistory.class, transactionHistoryDao);
        registerDao(User.class, userDao);
        registerDao(KeyStore.class, keyStoreDao);
    }
    
    public void clear() {
        uTXORecordDaoConfig.clearIdentityScope();
        transactionHistoryDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        keyStoreDaoConfig.clearIdentityScope();
    }

    public UTXORecordDao getUTXORecordDao() {
        return uTXORecordDao;
    }

    public TransactionHistoryDao getTransactionHistoryDao() {
        return transactionHistoryDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public KeyStoreDao getKeyStoreDao() {
        return keyStoreDao;
    }

}
