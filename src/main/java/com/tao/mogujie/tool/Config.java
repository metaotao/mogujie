package com.tao.mogujie.tool;

import java.io.*;
import java.util.Properties;

/**
 * 对配置文件的简单抽象
 * @time 2017-12-10
 * @author tao
 */
public class Config {

    protected Properties properties;

    /**
     * 根据配置文件的路径创建一个Config 对象
     *
     * 该类会首先在当前目录下搜寻这个配置文件，如果找不到
     * 会在classPath里找这个配置文件
     * @param path 配置文件的路径
     * @throws IOException 如果没有找到
     */
    public Config(String path)throws IOException{
        properties = new Properties();
        try{
            InputStream in = new BufferedInputStream (new FileInputStream(path));
            properties.load(in);
        }catch (Exception ex){
            try{
                InputStream stream = Config.class.getResourceAsStream("/"+path);
                if(stream == null) throw new IOException(path+ "Not found!");
                properties.load(stream);
            }catch (Exception e){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 根基一个Properties 创建一个配置
     * @param properties Properties对象
     */
    public Config(Properties properties){
        this.properties = properties;
    }

    /**
     * 获取一个Int类型的配置项 如果这个配置项没有配置 返回默认的值
     * @param key 配置项的key
     * @param defaultValue 默认值
     * @return Int的配置项
     */
    public int getInt(String key,int defaultValue){
        String value = properties.getProperty(key,Integer.toString(defaultValue));
        try{
            return Integer.parseInt(value);
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 获取一个String 类型的配置项 如果这个配置项没有值 返回默认值
     * @param key 配置项的key
     * @param defaultValue 默认的值
     * @return String类型的配置项
     */
    public String getString(String key,String defaultValue){
        String value = properties.getProperty(key);
        if(value == null) return defaultValue;
        return value;
    }

    /**
     *  获取一个Long 类型的配置项 如果这个配置项没有值 返回默认值
     * @param key 配置项的key
     * @param defaultValue 默认值
     * @return Long 类型的配置项
     */
    public long toLong(String key,int defaultValue){
        String value = properties.getProperty(key,Long.toString(defaultValue));
        try{
            return Long.parseLong(value);
        }catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * 获取一个Int类型的配置项
     * @param key 配置项的key
     * @return 配置的值
     * @throws NumberFormatException 如果配置项不存在或者配置项不是Int
     */
    public int getInt(String key){
        return Integer.parseInt(properties.getProperty(key));
    }

    /**
     * 获取一个Long类型的配置项
     * @param key 配置项的值
     * @return 配置项的值
     * @throws NumberFormatException 如果配置项的值不是Int 或者配置项的值不存在
     */
    public Long getLong(String key){
        return Long.parseLong(properties.getProperty(key));
    }

    /**
     * 获取一个Long 类型的配置项  如果这个配置项不存在 返回默认值
     * @param key 配置项的值
     * @param defaultValue 默认值
     * @return 配置项的值
     */
    public Long getLong(String key,long defaultValue){
        try{
            return Long.parseLong(properties.getProperty(key));
        }catch (Exception ex){
            return defaultValue;
        }
    }

    /**
     * 返回一个配置项的值
     * @param key 配置项的key
     * @return 配置项的值 如果配置项的值为null 返回 null
     */
    public String getString(String key){
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}