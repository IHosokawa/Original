/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

/**
 *
 * @author You
 */
public class RssData {
    
    private String title;
    private String itemTitle;
    private String itemUrl;
    
    public void initialization(){
        this.title = "サイトが見つかりません。";
        this.itemTitle = "コンテンツが見つかりません。";
        this.itemUrl = "https://www.google.co.jp";
    }
    
    public void error(String e){
        this.title = e;
        this.itemTitle = "サイトが見つかりません。";
        this.itemUrl = "https://www.google.co.jp";
    }
    
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        if(title != null){
            this.title = title;
        }else{
            this.title = "サイトが見つかりません";
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
}
