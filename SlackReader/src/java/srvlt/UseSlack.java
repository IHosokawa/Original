/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.ws.ProtocolException;

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
            urlcon.setRequestMethod("POST");
            urlcon.setDoOutput(true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
            String str = "";
            String body;
            while ((body = reader.readLine()) != null) {
                str += body + "\n";
            }
            System.out.println(str);
            //urlcon.connect();
            System.out.println("Success!!");
            //urlcon.disconnect();
        }catch (Exception e){
            System.out.println("error " + e.getMessage());
        }
    }
    
    public void upChkAndSay(LinkedHashMap<String,RegistData>beforeData, LinkedHashMap<String,RegistData>afterData, UserDataDTO udd){
        URL titleUrl ;
        try{
            for(Map.Entry<String,RegistData> val : beforeData.entrySet() ){
                
                if(afterData.get(val.getKey()).getRegistFlg() == 0){
                    if(!val.getValue().getItemTitle().equals(afterData.get(val.getKey()).getItemTitle())){
                        String itemTitle = URLEncoder.encode(afterData.get(val.getKey()).getItemTitle()+afterData.get(val.getKey()).getItemUrl(), "UTF-8");
                        titleUrl = new URL("https://slack.com/api/chat.postMessage?token=" + udd.getToken() + "&channel=" + udd.getSlackChannel() + "&text=" + itemTitle);
                        HttpURLConnection urlcon = (HttpURLConnection)titleUrl.openConnection();
                        urlcon.setRequestMethod("POST");
                        urlcon.setDoOutput(true);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlcon.getInputStream()));
                        String str = "";
                        String body;
                        while ((body = reader.readLine()) != null) {
                            str += body + "\n";
                        }
                        System.out.println(str);
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void testSlack() {
    String attachments = "[" +
            "{" +
            "\"color\": \"#89ceeb\"," +
            "\"text\": \"テスト\"" +
            "}" +
            "]";
    try {
        attachments= URLEncoder.encode(attachments,"UTF-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    try {
        URL url = new URL("https://slack.com/api/chat.postMessage?token=***************&channel=test2&text=test&attachments=" + attachments);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String str = "";
        String body;
        while ((body = reader.readLine()) != null) {
            str += body + "\n";
        }
        System.out.println(str);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (ProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
