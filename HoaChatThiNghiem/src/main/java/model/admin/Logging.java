package model.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class Logging {
    private long id_log;
    private int id_level;
    private long user_id;
    private String src;
    private String content;
    private String ip_address;
    private String web_browser;
    private Timestamp create_at;
    private String status;
    private static Logging install;
    public static Logging me(){
        if (install==null) install = new Logging();
        return install;
    }
    private Logging(){
    }

    /**
     * level: 0 ..., 1... , 2... , 3....
     * @param level
     */
//    private void log(int level,int userID,String src, String content){
//
//    }

}
