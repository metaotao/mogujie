package com.tao.mogujie.parser;

import com.tao.mogujie.tool.SystemTool;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NetConnectionImpl implements NetConnection {
    private int retryCount= SystemTool.RETRY_COUNT;
    private int sleeptime=SystemTool.SLEEP_TIME;
    private String[] user_agents = SystemTool.USER_AGENTS;
    private String user_agent;
    private String tmpagent = user_agents[0];
    private CloseableHttpClient httpClient;
    private HttpGet httpGet;
    private CloseableHttpResponse closeableHttpResponse;
    private HttpEntity httpEntity;
    //http://list.mogujie.com/search?sort=pop&fcid=50252&action=clothing&page=1
    public String getHtml(String url){
        String doc=null;
        try {
            httpClient= HttpClients.createDefault();
            //创建httpGet实例
            httpGet=new HttpGet(url);
            //执行httpget请求
            closeableHttpResponse=httpClient.execute(httpGet);
            httpEntity=closeableHttpResponse.getEntity();//获取返回实体
            doc=EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e1) {
                // e1.printStackTrace();
            }

        }
        return doc;
    }
}
