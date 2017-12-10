package com.tao.mogujie.model;

import com.tao.mogujie.tool.Config;

import java.io.IOException;

public class DBConfig extends Config{
    private String mysqlUrl;
    private String mysqlMogujieAlltypesTable;
    private String mysqlMogujieAlltypesclothesTable;
    private String mysqlMogujieGoodsinfoTable;
    private String mysqlUserName;
    private String mysqlUserPassword;

    public DBConfig(String path) throws IOException{
        super(path);
        this.mysqlUrl=getString("mysql_url");
        this.mysqlMogujieAlltypesTable=getString("mysql_mogujie_alltypes_table");
        this.mysqlMogujieAlltypesclothesTable=getString("mysql_mogujie_alltypesclothes_table");
        this.mysqlMogujieGoodsinfoTable=getString("mysql_mogujie_goodsinfo_table");
        this.mysqlUserName=getString("mysql_user_name");
        this.mysqlUserPassword=getString("mysql_user_password");
    }

    public String getMysqlMogujieAlltypesclothesTable() {
        return mysqlMogujieAlltypesclothesTable;
    }

    public String getMysqlMogujieAlltypesTable() {
        return mysqlMogujieAlltypesTable;
    }

    public String getMysqlUrl() {
        return mysqlUrl;
    }

    public String getMysqlMogujieGoodsinfoTable() {
        return mysqlMogujieGoodsinfoTable;
    }

    public String getMysqlUserName() {
        return mysqlUserName;
    }

    public String getMysqlUserPassword() {
        return mysqlUserPassword;
    }

    public void setMysqlMogujieAlltypesclothesTable(String mysqlMogujieAlltypesclothesTable) {
        this.mysqlMogujieAlltypesclothesTable = mysqlMogujieAlltypesclothesTable;
    }

    public void setMysqlMogujieAlltypesTable(String mysqlMogujieAlltypesTable) {
        this.mysqlMogujieAlltypesTable = mysqlMogujieAlltypesTable;
    }

    public void setMysqlMogujieGoodsinfoTable(String mysqlMogujieGoodsinfoTable) {
        this.mysqlMogujieGoodsinfoTable = mysqlMogujieGoodsinfoTable;
    }

    public void setMysqlUrl(String mysqlUrl) {
        this.mysqlUrl = mysqlUrl;
    }

    public void setMysqlUserName(String mysqlUserName) {
        this.mysqlUserName = mysqlUserName;
    }

    public void setMysqlUserPassword(String mysqlUserPassword) {
        this.mysqlUserPassword = mysqlUserPassword;
    }
}
