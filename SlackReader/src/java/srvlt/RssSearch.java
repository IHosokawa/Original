/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;


import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author You
 */
public class RssSearch {
    
    public static RssSearch getInstance(){
        return new RssSearch();
    }
    
    //RSSフィードから必要なサイトの情報を引き出す
    public RssData parseXML(String path) {
        RssData rd = new RssData();
        try {
            if(path != null || path.length() > 4 &&
                    path.substring(path.length()-4).equals(".xml") ||
                    path.substring(path.length()-4).equals(".rss") ||
                    path.substring(path.length()-4).equals(".rdf") ||
                    path.substring(path.length()-4).equals("rss1") ||
                    path.substring(path.length()-4).equals("rss2") ){
                DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder         builder = factory.newDocumentBuilder();
                Document                document = builder.parse(path);
                Element                 root = document.getDocumentElement();

                /* Get and print Title of RSS Feed. */
                NodeList                channel = root.getElementsByTagName("channel");
                NodeList                title = ((Element)channel.item(0)).getElementsByTagName("title");
                System.out.println("\nTitle: " + title.item(0).getFirstChild().getNodeValue() + "\n");

                /* Get Node list of RSS items */
                NodeList                item_list = root.getElementsByTagName("item");
                Element  element = (Element)item_list.item(0);
                NodeList item_title = element.getElementsByTagName("title");
                NodeList item_link  = element.getElementsByTagName("link");
                rd.setTitle(title.item(0).getFirstChild().getNodeValue());
                rd.setItemTitle(item_title.item(0).getFirstChild().getNodeValue());
                rd.setItemUrl(item_link.item(0).getFirstChild().getNodeValue());
            }else{
                rd.initialization();
            }
            
        } catch (IOException e) {
            System.out.println("IO Exception");
            rd.initialization();
        } catch (ParserConfigurationException e) {
            System.out.println("Parser Configuration Exception");
            rd.initialization();
        } catch (SAXException e) {
            System.out.println("SAX Exception");
            rd.initialization();
        }
        return rd;
    }
    
}
