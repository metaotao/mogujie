package com.tao.mogujie.model;

import java.util.List;

public class AllTypesClothesBean {
    /**
     * 所有种类下的细类
     */
    private String rid;
    private String types_id;
    private String clothesURL;
    private String clothesName;
    private List<GoodsInfoBean> goodsInfoBeans;

    public List<GoodsInfoBean> getGoodsInfoBeans() {
        return goodsInfoBeans;
    }

    public void setGoodsInfoBeans(List<GoodsInfoBean> goodsInfoBeans) {
        this.goodsInfoBeans = goodsInfoBeans;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRid() {
        return rid;
    }

    public String getClothesName() {
        return clothesName;
    }

    public String getClothesURL() {
        return clothesURL;
    }

    public String getTypes_id() {
        return types_id;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public void setClothesURL(String clothesURL) {
        this.clothesURL = clothesURL;
    }

    public void setTypes_id(String types_id) {
        this.types_id = types_id;
    }
}