package com.tao.mogujie.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tao.mogujie.model.AllTypesClothesBean;
import com.tao.mogujie.model.GoodsInfoBean;
import com.tao.mogujie.test.GoodsInfo;
import com.tao.mogujie.tool.SystemTool;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
public class MogujieUtil {
    private static final Logger LOG= LoggerFactory.getLogger(MogujieUtil.class);

    private int pageSum= SystemTool.PAGE_SUM;
    private int pageSize=SystemTool.PAGE_SIZE;
    private int pageNum=0;
    private String clothesForeURL=SystemTool.clothesForeURL;
    private String goodsForeURL=SystemTool.goodsForeURL;
    private CloseableHttpClient closeableHttpClient;
    private RequestConfig requestConfig;
    private String baseURL;
    public MogujieUtil(String baseURL) throws Exception{
        this.baseURL=baseURL;
        this.closeableHttpClient= HttpClients.createDefault();
        this.pageSize=0;
        requestConfig=RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .build();

    }

    public Optional<String> netConnect(HttpUriRequest request,int tryCounts){
        if (tryCounts<=5){
            try{
                CloseableHttpResponse response=closeableHttpClient.execute(request);
                String html= EntityUtils.toString(response.getEntity());
                LOG.info("Request success",request.getURI().toString());
                return Optional.of(html);

            }catch (IOException e){
                LOG.warn("Request error will try 5s later",request.getURI().toString());
                try{
                    Thread.sleep(6000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }
                return netConnect(request,tryCounts+1);

            }
        }else {
            return Optional.empty();
        }
    }

    public List<AllTypesClothesBean> allTypesClothes(){
        HttpGet get=new HttpGet(baseURL);
        get.setConfig(requestConfig);
        String html=netConnect(get,0).orElseGet(null);
        if (html!=null){
            Document document= Jsoup.parse(html,baseURL);
            Elements elements = document.select("div.type_sections");
            return elements.stream()
                    .map(element -> {
                        AllTypesClothesBean allTypesClothesBean=new AllTypesClothesBean();
                        Elements elems = element.select("a");
                        for (Element ele : elems) {
                            String[] initURL=ele.attr("href").split("[?]");
                            String clothesURL=clothesForeURL+initURL[0];
                            String title=ele.text();
                            allTypesClothesBean.setClothesURL(clothesURL);
                            allTypesClothesBean.setClothesName(title);
                            String num=clothesURL.split("/")[5];
                            System.out.println(clothesURL+","+title+","+num);
                        }
                        return allTypesClothesBean;
                    }).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public List<GoodsInfoBean> goodsInfoBeanList(AllTypesClothesBean allTypesClothesBean){
        MogujieGoodsClient mogujieGoodsClient=new MogujieGoodsClient(allTypesClothesBean);
        List<GoodsInfoBean> goodsInfoBeans=null;
        while (mogujieGoodsClient.hasNext()){
            goodsInfoBeans=mogujieGoodsClient.nextPage();
        }
        return goodsInfoBeans;
    }

    public class MogujieGoodsClient{
        private int pageNum;
        private List<GoodsInfoBean> goodsInfoBeans;
        private AllTypesClothesBean allTypesClothesBean;
        public MogujieGoodsClient(AllTypesClothesBean allTypesClothesBean){
            this.pageNum=1;
            this.allTypesClothesBean=allTypesClothesBean;
        }

        boolean hasNext(){
            pageNum+=1;
            return pageNum<=pageSize;
        }

        List<GoodsInfoBean> nextPage(){
            HttpGet get=new HttpGet(goodsForeURL+allTypesClothesBean.getClothesURL().split("/")[5]+"&action=clothing&page="+pageNum);
            get.setConfig(requestConfig);
            String html=netConnect(get,0).orElseGet(null);
            if (html!=null){
                JSONObject jsonObj= (JSONObject) JSON.parseObject(html).get("result");
                JSONObject resultObj=(JSONObject)jsonObj.get("wall");
                JSONArray result=resultObj.getJSONArray("list");
                //获得商品URL 商品现价 商品销量 商品收藏数
                this.goodsInfoBeans=JSON.parseArray(result.toJSONString(),GoodsInfoBean.class);
                allTypesClothesBean.setGoodsInfoBeans(goodsInfoBeans);
                return goodsInfoBeans;
            }else{
                return new ArrayList<>();
            }
        }

        GoodsInfoBean getGoodsInfo(String goodsURL){
            GoodsInfoBean goodsInfoBean=new GoodsInfoBean();
            HttpGet get=new HttpGet(goodsURL);
            get.setConfig(requestConfig);
            String html=netConnect(get,0).orElseGet(null);

            if (html!=null){
                Document document= Jsoup.parse(html,baseURL);
                //商品名称  商品评价数 店铺名
                Elements elements = document.select("h1.goods-title,span.num,div.shop-name fl");
                goodsInfoBean.setGoodsTitle(elements.get(0).text());
                int goodsCommentNum=Integer.parseInt(elements.get(1).text());
                goodsInfoBean.setGoodsCommentNum(goodsCommentNum);
                goodsInfoBean.setShopName(elements.get(2).text());
                this.goodsInfoBeans.add(goodsInfoBean);
            }
            return goodsInfoBean;
        }

    }
    public static void main(String[] args) throws Exception{
        MogujieUtil mogujieUtil=new MogujieUtil(SystemTool.baseURLS[0]);
      //  List<AllTypesBean> list=mogujieUtil.initPage();
        mogujieUtil.allTypesClothes();

    }

}
