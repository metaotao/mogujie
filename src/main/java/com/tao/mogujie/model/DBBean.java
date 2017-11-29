package com.tao.mogujie.model;

public class DBBean {
    private String drivename;
    private String urlConn;
    private String username;
    private String password;
    private String poolname="mogujiepool";
    private int minConnections;
    private int maxConnections;
    private long connTimeOut;
    private int maxActiveConnections;
    private long connectionTimeOut = 1000 * 60 * 20;
    private int initConnections = 5;
    private boolean isCurrentConnection = true;
    private boolean isCheckPool = true;
    private long lazyCheck = 1000 * 60 * 60;
    private long periodCheck = 1000 * 60 * 60;

    public DBBean(){

    }
    public DBBean(String drivename,String urlConn,String username,String password){
        super();
        this.drivename=drivename;
        this.urlConn=urlConn;
        this.username=username;
        this.password=password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDrivename(String drivename) {
        this.drivename = drivename;
    }

    public String getDrivename() {
        return drivename;
    }

    public void setUrlConn(String urlConn) {
        this.urlConn = urlConn;
    }

    public String getUrlConn() {
        return urlConn;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setMinConnections(int minConnections) {
        this.minConnections = minConnections;
    }

    public int getMinConnections() {
        return minConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getMaxConnections() {
        return maxConnections;
    }
    public long getConnTimeOut() {
        return connTimeOut;
    }

    public void setConnTimeOut(long connTimeOut) {
        this.connTimeOut = connTimeOut;
    }

    public int getMaxActiveConnections() {
        return maxActiveConnections;
    }

    public void setMaxActiveConnections(int maxActiveConnections) {
        this.maxActiveConnections = maxActiveConnections;
    }

    public long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public boolean isCurrentConnection() {
        return isCurrentConnection;
    }

    public void setCurrentConnection(boolean isCurrentConnection) {
        this.isCurrentConnection = isCurrentConnection;
    }

    public boolean isCheckPool() {
        return isCheckPool;
    }

    public void setCheckPool(boolean isCheckPool) {
        this.isCheckPool = isCheckPool;
    }

    public long getLazyCheck() {
        return lazyCheck;
    }

    public void setLazyCheck(long lazyCheck) {
        this.lazyCheck = lazyCheck;
    }

    public long getPeriodCheck() {
        return periodCheck;
    }

    public void setPeriodCheck(long periodCheck) {
        this.periodCheck = periodCheck;
    }

}
