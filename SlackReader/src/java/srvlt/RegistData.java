/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.HashMap;
import java.util.*;



/**
 *
 * @author You
 */
public class RegistData {
    private int registID;
    private String registUrl;
    private int registFlg;
    private int userID;
    private String userFlgStr;
    
    private String title;
    private String itemTitle;
    private String itemUrl;
    
    public static RegistData getInstance(){
        return new RegistData();
    }
    
    public int getRegistID(){
        return this.registID;
    }
    public void setRegistID(int registID){
        this.registID = registID;
    }
    
    public String getRegistUrl(){
        return this.registUrl;
    }
    public void setRegistUrl(String registUrl){
        if(registUrl != null){
            this.registUrl = registUrl;
        }else{
            this.registUrl = "";
        }
    }
    
    public int getRegistFlg(){
        return this.registFlg;
    }
    public void setRegistFlg(int registFlg){
        if( registFlg == 0 || registFlg == 1){
            this.registFlg = registFlg;
        }else{
            this.registFlg = 0;
        }
    }
    
    public String getUserFlgStr(){
        return this.userFlgStr;
    }
    public void setUserFlgStr(String userFlgStr){
        if(userFlgStr != null){
            this.userFlgStr = userFlgStr;
        }else{
            this.userFlgStr = "";
        }
    }
    
    public int getUserID(){
        return this.userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        if( title != null){
            this.title = title;
        }else{
            this.title = "サイトが見つかりません。";
        }
    }
    
    public String getItemTitle(){
        return this.itemTitle;
    }
    public void setItemTitle(String itemTitle){
        if(itemTitle != null){
            this.itemTitle = itemTitle;
        }else{
            this.itemTitle = "サイトが見つかりません。";
        }
    }
    
    public String getItemUrl(){
        return this.itemUrl;
    }
    public void setItemUrl(String itemUrl){
        if(itemUrl != null){
            this.itemUrl = itemUrl;
        }else{
            this.itemUrl = "https://www.google.co.jp";
        }
    }
    
    
    //HashMap<Integer,UserDataDTO>をHashMap<Integer,RegistData>へ変換
    public LinkedHashMap<String,RegistData> HMUDD2HMRDMappint(LinkedHashMap<String,UserDataDTO> map){
        LinkedHashMap<String,RegistData> rdMap = new LinkedHashMap<String,RegistData>();
        for(Map.Entry<String,UserDataDTO> val:map.entrySet()){
            RegistData regD = new RegistData();
            RssSearch rs = new RssSearch();
            regD.setRegistID(map.get(val.getKey()).getRegistID());
            regD.setRegistUrl(map.get(val.getKey()).getRegistUrl());
            //RSSURLからRSSフィードの情報を取得(修正予定)
            RssData rd = rs.parseXML(regD.getRegistUrl());
            regD.setTitle(rd.getTitle());
            regD.setItemTitle(rd.getItemTitle());
            regD.setItemUrl(rd.getItemUrl());
            regD.setRegistFlg(map.get(val.getKey()).getRegistFlg());
            regD.setUserID(map.get(val.getKey()).getUserID());
            rdMap.put(val.getKey(),regD);
        }
        return rdMap;
    }
    
    public void UDD2RDMappint(UserDataDTO udd){
        this.registID = udd.getRegistID();
        this.registUrl = udd.getRegistUrl();
        this.registFlg = udd.getRegistFlg();
        this.userID = udd.getUserID();
        
    }
    
    public LinkedHashMap<String,UserDataDTO> HMRDM2HMUDDMappint(LinkedHashMap<String,RegistData> map){
        LinkedHashMap<String,UserDataDTO> uddMap = new LinkedHashMap<String,UserDataDTO>();
        for(Map.Entry<String,RegistData> val : map.entrySet()){
            UserDataDTO udd = new UserDataDTO();
            udd.setUserID(val.getValue().getUserID());
            udd.setRegistID(val.getValue().getRegistID());
            udd.setRegistFlg(val.getValue().getRegistFlg());
            udd.setRegistUrl(val.getValue().getRegistUrl());
            uddMap.put(val.getKey(), udd);
        }
        return uddMap;
    }
}
