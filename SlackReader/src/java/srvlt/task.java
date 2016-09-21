/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author You
 */
public class task {
 
    /**
     * @param args
     */
    public static void main(String[] args) {


        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
 
            @Override
            public void run() {
                // ここに繰り返したい処理を書く
                System.out.println("action.");
            }
        },5*1000,5*1000);//実行感覚（ミリ秒）
        System.out.println("test");
        // 15分待つ
        try {
            Thread.sleep(/*15*/20*1000);//上記処理を実行させる時間？（ミリ秒）
            System.out.println("Therd.Sleep.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
 
        //timer.cancel();
    }
}
