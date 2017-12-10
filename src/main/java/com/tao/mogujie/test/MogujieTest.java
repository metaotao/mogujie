package com.tao.mogujie.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class MogujieTest {

    public MogujieTest() throws IOException{
        getJson();;
    }
    //创建httpclient实例
    public static void main(String[] args) throws IOException{
        new MogujieTest();
    }

    public String getClothesJson() throws IOException{
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet=new HttpGet("http://list.mogujie.com/search?sort=pop&fcid=50252&action=clothing&page=1");
        //执行httpget请求
        CloseableHttpResponse closeableHttpResponse=httpClient.execute(httpGet);
        HttpEntity httpEntity=closeableHttpResponse.getEntity();//获取返回实体
        String doc=EntityUtils.toString(httpEntity);

        System.out.println(doc);
        closeableHttpResponse.close();
        httpClient.close();
        return  doc;
    }

    public void getJson() throws IOException{
        String text=getClothesJson();
        JSONObject jsonObj= (JSONObject) JSON.parseObject(text).get("result");
        JSONObject resultObj=(JSONObject)jsonObj.get("wall");
        JSONArray result=resultObj.getJSONArray("list");
        List<LinksBean> linksBeans=JSON.parseArray(result.toJSONString(),LinksBean.class);
        for(LinksBean link:linksBeans){
            String url=link.getLink();
            String name=link.getTitle();
            System.out.println(url+"  "+name);
        }
    }
}
