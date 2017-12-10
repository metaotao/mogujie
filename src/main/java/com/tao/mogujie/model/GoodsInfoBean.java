package com.tao.mogujie.model;
public class GoodsInfoBean {
    private String rid;
    private String clothes_id;
    private String goodsTitle;
    private String goodsLink;
    private String goodsPrice;
    //评论数
    private int goodsCommentNum;
    //销量
    private int  goodsSalesNum;
    //店铺名
    private String shopName;
    //店铺描述评分 质量评分 服务评分
    private String shopDescribe;
    private String shopQuality;
    private String shopService;
    //商品收藏数
    private int connectionNum;

    public String getRid() {
        return rid;
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

    public int getConnectionNum() {
        return connectionNum;
    }

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }
    public String getGoodsTitle() {
        return goodsTitle;
    }

    public String getGoodsLink() {
        return goodsLink;
    }

    public int getGoodsCommentNum() {
        return goodsCommentNum;
    }

    public int getGoodsSalesNum() {
        return goodsSalesNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setGoodsCommentNum(int goodsCommentNum) {
        this.goodsCommentNum = goodsCommentNum;
    }

    public void setGoodsLink(String goodsLink) {
        this.goodsLink = goodsLink;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public void setGoodsSalesNum(int goodsSalesNum) {
        this.goodsSalesNum = goodsSalesNum;
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
