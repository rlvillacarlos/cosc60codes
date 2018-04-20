package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.ArrayList;
import edu.rlv.cosc60.List;
import edu.rlv.cosc60.MinHeapPQ;
import edu.rlv.cosc60.MinPQ;

/**
 *
 * @author russel
 */
public class AlarmManager {
    MinPQ<Alarm> pq = new MinHeapPQ<>();
    
    public void addAlarm(Alarm a){
        pq.add(a);
    }
    
    public List<Alarm> notifyTime(int time){
        List<Alarm> alarms = new ArrayList<>();
        
        while(!pq.isEmpty()){
            if(pq.getMin().getTime()<= time){
                Alarm a = pq.removeMin();
                alarms.add(a);
                if(a.getSnooze()> 0 && a.getRepeat()> 0){
                    Alarm newAlarm = new Alarm(a.getName(), time + a.getSnooze(), 
                                                a.getSnooze(), a.getRepeat()-1);
                    addAlarm(newAlarm);
                }
            }else{
                break;
            }
        }
        return alarms;
    }
    
    public List<Alarm> getScheduledAlarms(){
        List<Alarm> alarms = new ArrayList<>();
        for(Alarm a:pq){
            alarms.add(a);
        }
        return alarms;
    }    
    
}
