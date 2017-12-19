package com.tao.mogujie.model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class AllTypesClothesBean {
    /**
     * 所有种类下的细类
     */
    private String rid;
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
        return DigestUtils.md5Hex(clothesURL+clothesName);
    }

    public String getClothesName() {
        return clothesName;
    }

    public String getClothesURL() {
        return clothesURL;
    }

    public void setClothesName(String clothesName) {
        this.clothesName = clothesName;
    }

    public void setClothesURL(String clothesURL) {
        this.clothesURL = clothesURL;
    }

}
