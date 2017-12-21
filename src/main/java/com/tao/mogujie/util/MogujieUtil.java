package com.tao.mogujie.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tao.mogujie.model.AllTypesClothesBean;
import com.tao.mogujie.model.GoodsInfoBean;
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
    private int pageNum;
    private String clothesForeURL=SystemTool.clothesForeURL;
    private String goodsForeURL=SystemTool.goodsForeURL;
    private CloseableHttpClient closeableHttpClient;
    private RequestConfig requestConfig;
    private String baseURL;
    public MogujieUtil(String baseURL) throws Exception{
        this.baseURL=baseURL;
        this.closeableHttpClient= HttpClients.createDefault();
        this.pageNum=1;
       // this.pageSize=0;
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
    //获取所有类型下的商品细类
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
                            allTypesClothesBean.setRid(allTypesClothesBean.getRid());
                            allTypesClothesBean.setClothesURL(clothesURL);
                            allTypesClothesBean.setClothesName(title);
                            String num=clothesURL.split("/")[5];
                            System.out.println("all clothes:"+clothesURL+","+title+","+num);
                        }
                        return allTypesClothesBean;
                    }).collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public boolean hasNext(){
        pageNum+=1;
        return pageNum<=pageSize;
    }

    public List<GoodsInfoBean> nextPage(AllTypesClothesBean allTypesClothesBean){
        HttpGet get=new HttpGet(goodsForeURL+allTypesClothesBean.getClothesURL().split("/")[5]+"&action=clothing&page="+pageNum);
        get.setConfig(requestConfig);
        System.out.println("nextpage:"+goodsForeURL+allTypesClothesBean.getClothesURL().split("/")[5]+"&action=clothing&page="+pageNum);
        String html=netConnect(get,0).orElseGet(null);
        if (html!=null){
            JSONObject jsonObj= (JSONObject) JSON.parseObject(html).get("result");
            JSONObject resultObj=(JSONObject)jsonObj.get("wall");
            JSONArray result=resultObj.getJSONArray("list");
            return JSON.parseArray(result.toJSONString(),GoodsInfoBean.class);
        //    return linksBeanList;
        }else{
            return new ArrayList<>();
        }
    }

    public GoodsInfoBean goodsInfoBeanList(AllTypesClothesBean allTypesClothesBean,GoodsInfoBean goodsInfoBean){
        MogujieGoodsClient mogujieGoodsClient=new MogujieGoodsClient(allTypesClothesBean,goodsInfoBean);
        List<GoodsInfoBean> goodsInfoBeans=mogujieGoodsClient.goodsInfos();
        return goodsInfoBean;
    }

    public class MogujieGoodsClient{
        private List<GoodsInfoBean> goodsInfoBeans;
        private GoodsInfoBean goodsInfoBean;
        private AllTypesClothesBean allTypesClothesBean;
        public MogujieGoodsClient(AllTypesClothesBean allTypesClothesBean,GoodsInfoBean goodsInfoBean){

            this.allTypesClothesBean=allTypesClothesBean;
            this.goodsInfoBean=goodsInfoBean;
        }

        List<GoodsInfoBean> goodsInfos(){
            HttpGet get=new HttpGet(goodsInfoBean.getLink());
            get.setConfig(requestConfig);
            String html=netConnect(get,0).orElseGet(null);
            if (html!=null){
                Document document= Jsoup.parse(html,goodsInfoBean.getLink());
                //商品名称  商品现价 商品评价 累积销量 店铺名 店铺描述 质量 服务 收藏数
                Elements elements=document.select("h1.goods-title,#J_NowPrice,span.num,span.num J_SaleNum,a[title],span.s-cat,span.fav-num");
                GoodsInfoBean goodsInfo=new GoodsInfoBean();
                goodsInfoBeans.forEach(goodsInfoBean->{
                    goodsInfoBean=new GoodsInfoBean();
                    goodsInfoBean.setRid(goodsInfoBean.getRid());
                    goodsInfoBean.setClothes_id(allTypesClothesBean.getRid());
                    goodsInfoBean.setLink(goodsInfoBean.getLink());
                    System.out.println(goodsInfoBean.getLink()+elements.get(0).text());
                    goodsInfoBean.setGoodsTitle(elements.get(0).text());
                    goodsInfoBean.setPrice(elements.get(1).text());
                    int goodsCommentNum=Integer.parseInt(elements.get(2).text());
                    goodsInfoBean.setGoodsCommentNum(goodsCommentNum);
                    int salesNum=Integer.parseInt(elements.get(3).text());
                    goodsInfoBean.setSale(salesNum);
                    goodsInfoBean.setShopName(elements.get(4).text());
                    goodsInfoBean.setShopDescribe(elements.get(5).text());
                    goodsInfoBean.setShopQuality(elements.get(6).text());
                    goodsInfoBean.setShopService(elements.get(7).text());
                    int favNum=Integer.parseInt(elements.get(8).text());
                    goodsInfoBean.setCfav(favNum);
                });
              //  allTypesClothesBean.setGoodsInfoBeans(goodsInfoBeans);
                return this.goodsInfoBeans;
            }else{
                return new ArrayList<>();
            }
        }
    }

}
