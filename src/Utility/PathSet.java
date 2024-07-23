/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

public class PathSet {
    
    static private String tblReportPath="src\\Reports\\tableReport.jrxml";
    static private String orderListReportPath="D:\\Standalone project Backup\\Interface\\MLT 8.2.24\\src\\Reports\\OrderList.jrxml";
    static private String DBURL;
    
    public static void setTblReportPath(String path){
        tblReportPath=path;
    }
    
    public static String getTblReportPath(){
        return tblReportPath;
    }
    
    public static void setOrderListPath(String path){
        orderListReportPath=path;
    }
    
    public static String getOrderListPath(){
        return orderListReportPath;
    }
    
    public static void setDBUrl(String url){
        DBURL=url;
    }
    
    public static String getDBUrl(){
        return DBURL;
    }
}
