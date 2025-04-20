package database;

import bean.AbBean;

import java.util.List;

public class DB<E> {
    private static DB install;
    public static DB me(){
        if (install==null) install = new DB();
        return install;
    }
    private DB(){

    }
    public boolean insert(AbBean bean){
        return bean.insert(JDBiConnector.me());
    }


}
