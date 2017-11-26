package com.tao.mogujie.test;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class MogujieTest {
    private String strURL = "http://list.mogujie.com/book/";
    //创建httpclient实例
    CloseableHttpClient httpClient= HttpClients.createDefault();
    //创建httpGet实例
    HttpGet httpGet=new HttpGet(strURL);

}
