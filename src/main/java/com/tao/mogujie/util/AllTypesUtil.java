package com.tao.mogujie.util;

import com.tao.mogujie.model.AllTypesBean;
import com.tao.mogujie.parser.NetConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AllTypesUtil {
    private AllTypesBean allTypesBean;
    private NetConnection netConnection;
    private List<String> typesList;

    public AllTypesUtil(AllTypesBean allTypesBean, NetConnection netConnection) {
        this.allTypesBean = allTypesBean;
        this.netConnection = netConnection;
    }

    public List<String> getAllTypes(String url) {
        typesList=new ArrayList<String>();
        String html = netConnection.getHtml(url);
        Document doc= Jsoup.parse(html);
        Elements elements = doc.select("dt.nav_list_title");
        for (Element element : elements) {
            Elements elems = element.select("a");
            for (Element ele : elems) {
                String pageUrl = ele.attr("href");
                String title = ele.text();
                typesList.add(pageUrl);
                allTypesBean.setPageURL(pageUrl);
                allTypesBean.setTypesName(title);
                System.out.println(pageUrl + title);
            }
        }
        return typesList;
    }
}
