package com.tao.mogujie.crawler;

import com.tao.mogujie.dao.SaveInfoDao;
import com.tao.mogujie.tool.SystemTool;
import com.tao.mogujie.util.MogujieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerThread extends Thread{
    private static final Logger LOG= LoggerFactory.getLogger(CrawlerThread.class);
    private SaveInfoDao saveInfoDao;
    private MogujieUtil mogujieUtil;
    //private String[] baseURLS= SystemTool.baseURLS;
    public CrawlerThread(SaveInfoDao saveInfoDao,MogujieUtil mogujieUtil){
        this.mogujieUtil=mogujieUtil;
        this.saveInfoDao=saveInfoDao;
    }

    public void run(){
        while (mogujieUtil.hasNext()){
            try{
                mogujieUtil.allTypesClothes().forEach(a->{
                    if(a!=null){
                        saveInfoDao.save(a);
                        mogujieUtil.nextPage(a).forEach(b->{
                            b=mogujieUtil.goodsInfoBeanList(a,b);
                            if (b!=null){
                                saveInfoDao.save(b);
                            }
                        });
                    }
                });
                Thread.sleep(2000);
            }catch (Exception ex){
                LOG.warn("crawl failed!",ex);
            }

        }
    }
}
