package dev.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static ConnectionUtil connectionUtil;

    private ConnectionUtil(){}

    public static ConnectionUtil getConnectionUtil(){
        if(connectionUtil == null){
            return new ConnectionUtil();
        }
        return connectionUtil;
    }
    public Connection getConnection(){
        Connection conn = null;
        String url = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASS");
        try{
            conn = DriverManager.getConnection(url, username, password);

        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
//    public static void main(String[] args){
//        try(Connection conn = ConnectionUtil.getConnectionUtil().getConnection()){
//            if(conn != null){
//                System.out.println("Connection Successful");
//            }else System.out.println("Conneciton unsuccessful");
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//    }
}

