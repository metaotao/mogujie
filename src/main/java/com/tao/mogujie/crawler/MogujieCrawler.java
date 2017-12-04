package com.tao.mogujie.crawler;

import com.tao.mogujie.model.AllTypesBean;
import com.tao.mogujie.model.GoodsInfoBean;
import com.tao.mogujie.parser.NetConnection;
import com.tao.mogujie.parser.NetConnectionImpl;
import com.tao.mogujie.test.GoodsInfo;
import com.tao.mogujie.tool.SystemTool;
import com.tao.mogujie.util.AllTypesUtil;

/**
 * http://h5.mogujie.com/detail-normal/index.html?itemId=1kplzie&acm=3.ms.1_4_1kplzie.17.1308-58251.gWIf2qCQcUaqT.t_gWIf2qCQcUaqT-lc_16  冬季新款大真毛领呢子大衣女韩版外套女收腰羊毛呢夹棉过膝外套女
 http://shop.mogujie.com/detail/1kplzie?acm=3.ms.1_4_1kplzie.17.1308-58251.gWIf2qCQcUaqT.t_gWIf2qCQcUaqT-lc_16&ptp=1.L0XuHb.0.0.CWxVdY1
 http://h5.mogujie.com/detail-normal/index.html?itemId=1kqie48&acm=3.ms.1_4_1kqie48.17.1308-58251.gWIf2qCQcUaqT.t_gWIf2qCQcUaqT-lc_16  冬季新款韩版中长款过膝呢子大衣小个子拼色翻领单排扣毛呢外套女
 http://h5.mogujie.com/detail-normal/index.html?itemId=1klj75k&acm=3.ms.1_4_1klj75k.17.1308-58251.gWIf2qCQcUaqT.t_gWIf2qCQcUaqT-lc_16
 */
public class MogujieCrawler {
    private AllTypesBean allTypesBean;
    private NetConnection netConnection;
    private AllTypesUtil allTypesUtil;
    private GoodsInfoBean goodsInfoBean;
    private GoodsInfo goodsInfo;
    String baseURL= SystemTool.baseURL;
    String goodsURL="http://shop.mogujie.com/detail/1knrbk0?acm=3.ms.1_1_1knrbk0.17.28075-58251.7M5trqCQfy5GT.t_7M5trqCQfy5GT-lc_16-c_2_16_304023317_963243254_1_0-c1_50252_nil_nil_2_963243254_25&cparam=MTUxMjM5MTI0OF8xOXhjdTFtXzBjNzlmNmViYjQxN2Q2MzIyY2JlM2IxMjdiNzhhY2Q2XzE2XzBfMzA0MDIzMzE3XzRjODdfMF8xXzBfNTg0XzFfMF9ycy01MDI1Mi5ydC0wLnd0LTA&ptp=1.L0XuHb.0.0.zeF85ru";
    public MogujieCrawler(){
        netConnection=new NetConnectionImpl();
        getGoodsInfo();
    }
    public void getAllTypes(){
        allTypesBean=new AllTypesBean();
        allTypesUtil=new AllTypesUtil(allTypesBean,netConnection);
        allTypesUtil.getAllTypes(baseURL);
    }
    public void getGoodsInfo(){
        goodsInfoBean=new GoodsInfoBean();
        goodsInfo=new GoodsInfo(goodsInfoBean,netConnection);
        goodsInfo.getGoodsInfo(goodsURL);
    }
    public static void main(String[] args){
        new MogujieCrawler();
    }
}
