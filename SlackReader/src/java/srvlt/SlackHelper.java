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
public class SlackHelper {
    
    private final String INDEX_URL = "/index.jsp";
    private final String CONTACT_URL = "/SlackReader/contact.jsp";
    
    public static SlackHelper getInstance(){
        return new SlackHelper();
    }
    
    public String home(){
        return "<a href=\"" + INDEX_URL + "\">トップページへ</a>";
    }
    
    public String contact(){
        return "<a href=\"" + CONTACT_URL + "\">お問い合わせ<a/>";
    }
    /**RSS1.0とRSS2.0以降で日付の取得方法が違う
     * RSS1.0 dc:date
     * RSS2.0 pubDate
     * 参考http://on-ze.com/archives/1778
     */
}
