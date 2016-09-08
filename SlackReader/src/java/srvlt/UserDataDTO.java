/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.HashMap;
/**
 *
 * @author You
 */
public class UserDataDTO {
    
    private int userID;
    private String userName;
    private String password;
    private int delFlg;
    private String slackChannel;
    
    public int getUserID(){
        return this.userID;
    }
    public void setUserID(int id){
        this.userID = id;
    }
    
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String name){
        this.userName = name;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    
    public String getSlackChannel(){
        return this.slackChannel;
    }
    public void setSlackChannel(String channel){
        this.slackChannel = channel;
    }
    
    public int getDelFlg(){
        return this.delFlg;
    }
    public void setDelFlg(int del){
        this.delFlg = del;
    }
    
    
    private int registID;
    private String registUrl;
    private int registFlg;
    private String userFlgStf;
    
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
        return this.userFlgStf;
    }
    public void setUserFlgStr(String userFlgStr){
        if(userFlgStf != null){
            this.userFlgStf = userFlgStr;
        }else{
            this.userFlgStf = "";
        }
    }
    
    HashMap registData;
    public HashMap getRegistData(){
        return this.registData;
    }
    public void setRegistData(HashMap map){
        this.registData = map;
    }
}
