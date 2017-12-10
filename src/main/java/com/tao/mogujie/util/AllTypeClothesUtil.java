package com.tao.mogujie.util;

import com.tao.mogujie.model.AllTypesClothesBean;
import com.tao.mogujie.parser.NetConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class AllTypeClothesUtil {
    private AllTypesClothesBean allTypesClothesBean;
    private NetConnection netConnection;
    private AllTypesUtil allTypesUtil;

    public AllTypeClothesUtil(AllTypesClothesBean allTypesClothesBean,NetConnection netConnection,AllTypesUtil allTypesUtil){
        this.allTypesClothesBean=allTypesClothesBean;
        this.netConnection=netConnection;
        this.allTypesUtil=allTypesUtil;
    }

    public List<String> getTypesClothes(String url){
        List<String> clothesList=new ArrayList<String>();
        String pageContent=netConnection.getHtml(url);
        Document doc= Jsoup.parse(pageContent);
        Elements elements = doc.select("div.type_sections");
        System.out.println(elements.get(0).text());
        for (Element element : elements) {
            Elements elems = element.select("a");
            for (Element ele : elems) {
                String pageUrl = ele.attr("href");
                String title = ele.text();
                allTypesClothesBean.setClothesURL(pageUrl);
                allTypesClothesBean.setClothesName(title);
                clothesList.add(pageUrl);
                System.out.println(pageUrl + title);
            }
        }
        return clothesList;
    }

}
