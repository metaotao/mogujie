package com.tao.mogujie.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MogujieTest {

    //创建httpclient实例
    public static void main(String[] args) throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet=new HttpGet("http://list.mogujie.com/search?sort=pop&fcid=50252&action=clothing&page=1");
        //执行httpget请求
        CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity=closeableHttpResponse.getEntity();//获取返回实体
        System.out.println(EntityUtils.toString(httpEntity));
        closeableHttpResponse.close();
        httpClient.close();
    }



}
