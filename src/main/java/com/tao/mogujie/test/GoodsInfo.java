package com.tao.mogujie.test;

import com.tao.mogujie.model.GoodsInfoBean;
import com.tao.mogujie.parser.NetConnection;
import org.jsoup.Jsoup;
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
        String pageContent=netConnection.getHtml(url);
        Document doc= Jsoup.parse(pageContent);

        Elements elements = doc.select("div.type_sections");
        for (Element element : elements) {
            Elements elems = element.select("a");
            for (Element ele : elems) {
                String pageUrl = ele.attr("href");
                String title = ele.text();

                System.out.println(pageUrl + title);
            }
        }
      //  System.out.println(elements.get(0).text());
      //  Document doc = netConnection.getHtml(url);
        //System.out.println(doc);
        //商品名称  商品现价 商品评价 累积销量  收藏数 店铺名
      /*  Elements elements = doc.select("h1.goods-title,#J_NowPrice,span.num,span.num J_SaleNum,span.fav-num,div.shop-name fl");
        Element ele1=elements.get(0);
        Element ele2=elements.get(1);
        Element ele3=elements.get(2);
        Element ele4=elements.get(3);
        Element ele5=elements.get(4);
        Element ele6=elements.get(5);
        System.out.println(ele1.text());
        System.out.println(ele2.text());
        System.out.println(ele3.text());
        System.out.println(ele4.text());
        System.out.println(ele5.text());
        System.out.println(ele6.text());
   //     System.out.println(elements);
        for (Element element : elements) {
         //   System.out.println(element);
            Elements elems = element.select("a");
            for (Element ele : elems) {
                String pageUrl = ele.attr("href");
                String title = ele.text();

           //     System.out.println(pageUrl + title);
            }
        }*/
    }

}
