package com.tao.mogujie.tool;

public class SystemTool {
    public static final int SLEEP_TIME = 1500;
    public static final int CONN_TIME_OUT = 3 * 1000;
    public static final String[] USER_AGENTS= {
            "User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
            "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.75 Safari/537.36 LBBROWSER" };

    public static String baseURL="http://www.mogujie.com/";
    public static final String DEFAULT_ENCODE = "UTF-8";
    public static final int PAGE_SIZE = 60;
    public static final int PAGE_SUM = 100;
    public static final int DEFAULT_MAX_THREAD = 10;
    public static final int RETRY_COUNT = 3;
}
