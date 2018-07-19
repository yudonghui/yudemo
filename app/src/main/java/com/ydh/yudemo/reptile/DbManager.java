package com.ydh.yudemo.reptile;

import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by Android on 2018/5/2.
 */

public class DbManager {
    private org.xutils.DbManager.DaoConfig mDaoConfig;
    public DbManager(){
        mDaoConfig= new org.xutils.DbManager.DaoConfig();
        mDaoConfig.setDbName("reptil");
        mDaoConfig.setDbVersion(1);
        mDaoConfig.setDbUpgradeListener(new org.xutils.DbManager.DbUpgradeListener() {
            @Override
            public void onUpgrade(org.xutils.DbManager db, int oldVersion, int newVersion) {

            }
        });
    }
    public void inserts(ReptilDb reptilDb) {
        try {
            x.getDb(mDaoConfig).save(reptilDb);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void delete(ReptilDb reptilDb){
        try {
            x.getDb(mDaoConfig).delete(reptilDb);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void deleteAll(){
        try {
            x.getDb(mDaoConfig).delete(ReptilDb.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void deleteWhere(String issue)  {
        try {
            x.getDb(mDaoConfig).delete(ReptilDb.class, WhereBuilder.b("issue","=",issue));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public List<ReptilDb> queryAll(){
        try {
            return x.getDb(mDaoConfig).findAll(ReptilDb.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}
