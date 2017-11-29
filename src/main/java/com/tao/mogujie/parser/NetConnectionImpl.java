package com.tao.mogujie.parser;

import com.tao.mogujie.tool.SystemTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NetConnectionImpl implements NetConnection {
    private int retryCount= SystemTool.RETRY_COUNT;
    private int sleeptime=SystemTool.SLEEP_TIME;
    private String[] user_agents = SystemTool.USER_AGENTS;
    private String user_agent;
    private String tmpagent = user_agents[0];

    public Document getDocument(String url){
        Document doc = null;
        try {
            user_agent = tmpagent;
            doc = Jsoup.connect(url).userAgent(user_agent)
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                    .header("scheme", "https")
                    .header("version", "HTTP/1.1")
                    .header("accept-encoding", "gzip, deflate, sdch")
                    .header("accept-language", "zh-CN,zh;q=0.8")
                    //	.header("cookie", "bid=\"Q5KWZL7y8g7\";")
                    .header("cache-control", "max-age=0")
                    .get();

        } catch (Exception e) {
            try {
                Thread.sleep(sleeptime);
            } catch (InterruptedException e1) {
                // e1.printStackTrace();
            }
            retryCount--;
            if (retryCount > 0) {
                tmpagent = user_agents[retryCount];
                getDocument(url);
            }
        }
        return doc;
    }
}
