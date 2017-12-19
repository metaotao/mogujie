package com.tao.mogujie.crawler;

import com.tao.mogujie.dao.SaveInfoDao;
import com.tao.mogujie.model.DBConfig;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import com.tao.mogujie.tool.SystemTool;
import com.tao.mogujie.util.MogujieUtil;

import java.util.List;

public class MogujieCrawler {

    List<String> baseURLs= SystemTool.baseURLS;
    private MogujieUtil mogujieUtil;
    private SaveInfoDao saveInfoDao;
    private DBConfig dbConfig;
    public MogujieCrawler(){

    }

    public void run() throws Exception{
        dbConfig=new DBConfig("conf/mogujie.conf");
        saveInfoDao=new SaveInfoDao(dbConfig);
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(SystemTool.DEFAULT_MAX_THREAD);
        while (!baseURLs.isEmpty()){
            mogujieUtil=new MogujieUtil(getGoodsURL());
            while(true){
                if(pool.getActiveCount() < SystemTool.DEFAULT_MAX_THREAD){
                    break;
                }else{
                    Thread.sleep(2000);
                }
            }
            Thread crawlerThread = new CrawlerThread(saveInfoDao,mogujieUtil);
            pool.execute(crawlerThread);
        }

    }

    public String getGoodsURL() {
        return baseURLs.remove(0);
    }

    public static void main(String[] args){
        new MogujieCrawler();
    }
}
