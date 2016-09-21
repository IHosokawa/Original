/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srvlt;

import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author You
 */
public class ReloadTask {
    UserDataDTO uddRT;
    LinkedHashMap <String,RegistData> registDataRD;
    public static ReloadTask getInstance(){
        return new ReloadTask();
    }
    
    public LinkedHashMap<String,RegistData> autoUp(UserDataDTO udd){
        Timer timer = new Timer();
        this.uddRT = udd; 
        timer.schedule(new TimerLabelTask(), 0, 5*60*1000);
        return this.registDataRD;
    }
    class TimerLabelTask extends TimerTask{
        
        @Override
        public void run(){
            task();
        }
    }
    
    public void task(){
        try{
            
            LinkedHashMap<String,UserDataDTO> registData = UserDataDAO.getInstance().loginDataSeach(this.uddRT);
            //登録データから登録先の情報と必要な情報のみにマッピング
            this.registDataRD = RegistData.getInstance().HMUDD2HMRDMappint(registData);
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
