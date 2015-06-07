package com.luozhihuan.smartcode.model;

/**
 * Created by luozhihuan on 15/6/6.
 */
public class DBInfoModel {


    public DBInfoModel(DBUIInfoModel dbuiInfoModel){
        this.dbAddress = dbuiInfoModel.getDbTextField().getText();
        this.userName = dbuiInfoModel.getUsrTextField().getText();
        this.password = dbuiInfoModel.getPassTextField().getText();
        this.tableName = dbuiInfoModel.getTableTextField().getText();
    }
    private String dbAddress;

    private String userName;

    private String password;

    private String tableName;

    public String getDbAddress() {
        return dbAddress;
    }

    public void setDbAddress(String dbAddress) {
        this.dbAddress = dbAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
