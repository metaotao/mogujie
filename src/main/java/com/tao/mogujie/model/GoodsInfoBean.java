package com.tao.mogujie.model;
import org.apache.commons.codec.digest.DigestUtils;
public class GoodsInfoBean {
    private String rid;
    private String clothes_id;
    private String goodsTitle;
    private String link;
    private String price;
    //评论数
    private int goodsCommentNum;
    //销量
    private int  sale;
    //店铺名
    private String shopName;
    //店铺描述评分 质量评分 服务评分
    private String shopDescribe;
    private String shopQuality;
    private String shopService;
    //商品收藏数
    private int cfav;

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getCfav() {
        return cfav;
    }

    public void setCfav(int cfav) {
        this.cfav = cfav;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getRid() {
        return DigestUtils.md5Hex(link+goodsTitle+shopName);
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getClothes_id() {
        return clothes_id;
    }

    public void setClothes_id(String clothes_id) {
        this.clothes_id = clothes_id;
    }


    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }
    public String getGoodsTitle() {
        return goodsTitle;
    }

    public int getGoodsCommentNum() {
        return goodsCommentNum;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setGoodsCommentNum(int goodsCommentNum) {
        this.goodsCommentNum = goodsCommentNum;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopQuality() {
        return shopQuality;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopService() {
        return shopService;
    }

    public void setShopQuality(String shopQuality) {
        this.shopQuality = shopQuality;
    }

    public void setShopService(String shopService) {
        this.shopService = shopService;
    }
}
