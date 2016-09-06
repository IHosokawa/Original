/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.ArrayList;

/**
 *
 * @author You
 */
public class UserData {
    
    private int userID;
    private String userName;
    private String password;
    private int delFlg;
    private int registID;
    private String registUrl;
    private int registFlg;
    private ArrayList al;
    
    public int getUserID(){
        return this.userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String name){
        if(name != null){
            this.userName = name;
        }else{
            this.userName = "";
        }
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String pass){
        if(pass != null){
            this.password = pass;
        }else{
            this.password = "";
        }
    }
    
    public int getDelFlg(){
        return this.delFlg;
    }
    public void setDelFlg(int delFlg){
        //渡された値が0、あるいは1以外だったら0をセットする
        if(delFlg == 0 || delFlg == 1){
            this.delFlg = delFlg;
        }else{
            this.delFlg = 0; 
        }
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
    
    
    //DTO形式にマッピング
    public void UD2DTOMapping(UserDataDTO udd){
        udd.setUserID(this.userID);
        udd.setUserName(this.userName);
        udd.setPassword(this.password);
        udd.setDelFlg(this.delFlg);
        udd.setRegistID(this.registID);
        udd.setRegistUrl(this.registUrl);
        udd.setRegistFlg(this.registFlg);
        
    }
    
    //UD形式にマッピング
    public void DTO2UDMapping(UserDataDTO udd){
        this.userID = udd.getUserID();
        this.userName = udd.getUserName();
        this.password = udd.getPassword();
        this.delFlg = udd.getDelFlg();
        this.registID = udd.getRegistID();
        this.registUrl = udd.getRegistUrl();
        this.registFlg = udd.getRegistFlg();
    }
}
