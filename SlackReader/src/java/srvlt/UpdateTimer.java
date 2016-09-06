/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author You
 */
public class UpdateTimer {
    final Timer timer = new Timer();
        
    public static UpdateTimer getInstance(){
        return new UpdateTimer();
    }
    
    //
    public void autoUpdate(){
        
        this.timer.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run(){
                
            }
        }, 5*60*1000, 5*60*1000);
    }
    
    
    
    //TimerStopper
    public void stopUpdate(){
        this.timer.cancel();
    }
}
