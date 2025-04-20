package utils;

import bean.Log;
import database.DB;

import javax.servlet.http.HttpServletRequest;

public class WritingLogUtils {

    public WritingLogUtils(){}
    public static void writeLog(HttpServletRequest request, Log log){
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String webBrowser = request.getHeader("User-Agent");
        log.setWeb_browser(webBrowser);
        log.setIp_address(ipAddress);
        DB.me().insert(log);
    }
}
