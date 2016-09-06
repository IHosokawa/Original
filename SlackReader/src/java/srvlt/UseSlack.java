/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author You
 */
public class UseSlack {
    public static UseSlack getInstance(){
        return new UseSlack();
    }
    
    public void createChannel(URL url){
        try{
            HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
            urlcon.setRequestMethod("GET");
            urlcon.setInstanceFollowRedirects(false);
            urlcon.connect();
            System.out.println("Success!!");
            urlcon.disconnect();
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
        }
    }
}
