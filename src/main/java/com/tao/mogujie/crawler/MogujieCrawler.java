package com.tao.mogujie.crawler;

import com.tao.mogujie.model.AllTypesBean;
import com.tao.mogujie.parser.NetConnection;
import com.tao.mogujie.parser.NetConnectionImpl;
import com.tao.mogujie.tool.SystemTool;
import com.tao.mogujie.util.AllTypesUtil;

public class MogujieCrawler {
    private AllTypesBean allTypesBean;
    private NetConnection netConnection;
    private AllTypesUtil allTypesUtil;
    String baseURL= SystemTool.baseURL;
    public MogujieCrawler(){
        allTypesBean=new AllTypesBean();
        netConnection=new NetConnectionImpl();
        allTypesUtil=new AllTypesUtil(allTypesBean,netConnection);
        allTypesUtil.getAllTypes(baseURL);
    }
    public static void main(String[] args){
        new MogujieCrawler();
    }
}
