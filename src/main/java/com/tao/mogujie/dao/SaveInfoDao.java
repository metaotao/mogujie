package com.tao.mogujie.dao;

import com.tao.mogujie.model.AllTypesClothesBean;
import com.tao.mogujie.model.DBConfig;
import com.tao.mogujie.model.GoodsInfoBean;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveInfoDao {
    private DBConfig config;
    private BasicDataSource dataSource;
    public SaveInfoDao(DBConfig config){
        this.config=config;
        dataSource=new BasicDataSource();
        dataSource.setUrl(config.getMysqlUrl());
        dataSource.setUsername(config.getMysqlUserName());
        dataSource.setPassword(config.getMysqlUserPassword());
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    }
    public void save(AllTypesClothesBean allTypesClothesBean){
        try{
            Connection connection=dataSource.getConnection();
            PreparedStatement preparedStatement=
                    connection.prepareStatement("insert into "+config.getMysqlMogujieAlltypesclothesTable()+" "+
                            "values(?,?,?)");
            preparedStatement.setString(1,allTypesClothesBean.getRid());

            preparedStatement.setString(3,allTypesClothesBean.getClothesURL());
            preparedStatement.setString(4,allTypesClothesBean.getClothesName());
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void save(GoodsInfoBean goodsInfoBean){
        try{
            Connection connection=dataSource.getConnection();
            PreparedStatement preparedStatement=
                    connection.prepareStatement("insert into "+config.getMysqlMogujieGoodsinfoTable()+" "+
                            "values(?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,goodsInfoBean.getRid());
            preparedStatement.setString(2,goodsInfoBean.getClothes_id());
            preparedStatement.setString(3,goodsInfoBean.getLink());
            preparedStatement.setString(4,goodsInfoBean.getGoodsTitle());
            preparedStatement.setString(5,goodsInfoBean.getPrice());
            preparedStatement.setInt(6,goodsInfoBean.getGoodsCommentNum());
            preparedStatement.setInt(7,goodsInfoBean.getSale());
            preparedStatement.setString(8,goodsInfoBean.getShopName());
            preparedStatement.setString(9,goodsInfoBean.getShopDescribe());
            preparedStatement.setString(10,goodsInfoBean.getShopQuality());
            preparedStatement.setString(11,goodsInfoBean.getShopService());
            preparedStatement.setInt(12,goodsInfoBean.getCfav());
            preparedStatement.executeUpdate();

        }catch (SQLException e){

        }
    }
}
