package com.tao.mogujie.test;

import com.tao.mogujie.model.GoodsInfoBean;
import com.tao.mogujie.parser.NetConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoodsInfo {
    private GoodsInfoBean goodsInfoBean;
    private NetConnection netConnection;
    public GoodsInfo(GoodsInfoBean goodsInfoBean,NetConnection netConnection){
        this.goodsInfoBean=goodsInfoBean;
        this.netConnection=netConnection;
    }
    public void getGoodsInfo(String url){
        Document doc = netConnection.getDocument(url);
        //System.out.println(doc);
        //商品名称  商品现价
        Elements elements = doc.select("h1.goods-title,#J_NowPrice,span.num,span.num J_SaleNum");
        Element ele1=elements.get(0);
        Element ele2=elements.get(1);
        Element ele3=elements.get(2);
        Element ele4=elements.get(3);
        System.out.println(ele1.text());
        System.out.println(ele2.text());
        System.out.println(ele3.text());
        System.out.println(ele4.text());
   //     System.out.println(elements);
        for (Element element : elements) {
         //   System.out.println(element);
            Elements elems = element.select("a");
            for (Element ele : elems) {
                String pageUrl = ele.attr("href");
                String title = ele.text();

           //     System.out.println(pageUrl + title);
            }
        }
    }

}
