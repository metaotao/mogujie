package com.tao.mogujie.util;

<<<<<<< HEAD
import com.tao.mogujie.model.AllTypesBean;
import com.tao.mogujie.parser.NetConnection;
import com.tao.mogujie.tool.SystemTool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AllTypesUtil {
    private AllTypesBean allTypesBean;
    private NetConnection netConnection;
    public AllTypesUtil(AllTypesBean allTypesBean,NetConnection netConnection){
        this.allTypesBean=allTypesBean;
        this.netConnection=netConnection;
    }
    public void getAllTypes(String url){
        Document doc=netConnection.getDocument(url);
        Elements elements=doc.select("dt.nav_list_title");
        for (Element element:elements){
            Elements elems=element.select("a");
            for (Element ele:elems){
                String pageUrl=ele.attr("href");
                String title=ele.text();
                System.out.println(pageUrl+title);
            }

        }
    }

=======
import com.tao.mogujie.tool.SystemTool;

public class AllTypesUtil {
    private String basURL= SystemTool.baseURL;
    
>>>>>>> a317ec3e01cc0e0e6a5b3dab5af5752ed3c2592f
}
