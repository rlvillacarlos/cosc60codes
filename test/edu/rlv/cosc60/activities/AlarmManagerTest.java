package edu.rlv.cosc60.activities;

import edu.rlv.cosc60.ArrayList;
import edu.rlv.cosc60.List;
import static edu.rlv.cosc60.matchers.AlarmManagerMatcher.*;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author russel
 */
public class AlarmManagerTest {    
    /**
     * Test of addAlarm method, of class AlarmManager.
     */
    
    private static AlarmManager mngr;
    private static List<Alarm> insertedAlarms;
    private static List<Alarm> alarms;
    private static int maxInsert = 140000;

    
    @BeforeClass
    public static void init(){
        alarms = new ArrayList<>();
        insertedAlarms = new ArrayList<>();
        mngr = new AlarmManager();
        
        for(int i = 1;i<=maxInsert;i++){
            Alarm a = new Alarm("Alarm " + i, i, 0, 0);
            insertedAlarms.add(a);
            mngr.addAlarm(a);
        }
        
        for(int i = 1;i<=maxInsert;i++){
            Alarm a = new Alarm("Alarm " + i, maxInsert + i, 0, 0);
            alarms.add(a);
        }
    }
    
    @Test
    public void testAddAlarm_ShouldAddSchedule() {     
        System.out.println("Test addAlarm(): Should Snooze Correctly");
        assertThat(new AlarmManager(),canScheduleAlarm(new Alarm("Alarm1", 1, 0, 0)));
    }
    

    /**
     * Test of notifyTime method, of class AlarmManager.
     */
    @Test
    public void testNotifyTime_ShouldScheduleCorrectly() {   
        AlarmManager mngr = new AlarmManager();
        List<Integer> times = new ArrayList<>();
        List<Alarm> alarms = new ArrayList<>();
        Random rnd = new Random(System.currentTimeMillis());

        for (int i = 1; i <= 5; i++) {
            times.add(rnd.nextInt(100));
        }
                
        int i = 1;
        for (Integer time : times) {
            String name = String.format("Alarm %d", i);
            Alarm a = new Alarm(name, time, 0, 0);
            mngr.addAlarm(a);
            alarms.add(a);
            i++;
        }
        System.out.println("Test notifyTime(): Should Snooze Correctly");
        System.out.print("   Alarms: ");
        System.out.println(alarms);
        assertThat(mngr,canScheduleCorrectly(alarms));
    }
    
    @Test
    public void testNotifyTime_ShouldSnoozeCorrectly() {   
        AlarmManager mngr = new AlarmManager();
        Random rnd = new Random(System.currentTimeMillis());
        List<Alarm> alarms = new ArrayList<>();
        
        for(int i = 1;i<=5;i++){
            String name = String.format("Alarm %d", i);
            int snooze = rnd.nextInt(10) + 1;
            int repeat = rnd.nextInt(5) + 1;

            Alarm a = new Alarm(name, 5, snooze, repeat);
            alarms.add(a);
            mngr.addAlarm(a);
        }
        System.out.println("Test notifyTime(): Should Snooze Correctly");
        System.out.print("   Alarms: ");
        System.out.println(alarms);
        
        assertThat(mngr,canSnoozeCorrectly(alarms));
    }
    @Test(timeout = 2000)
    public void testAddAlarmAndNotifyTime_ShouldNotExceedTimeLimit() {     
        System.out.println("Test addAlarm() and notifyTime(): Should Not Exceed Time Limit");

        for(Alarm a:alarms){
            mngr.addAlarm(a);
        }
        
        for(Alarm a:insertedAlarms){
            int time = a.getTime();
            List<Alarm> expiredAlarms = mngr.notifyTime(time);
            
            if(expiredAlarms.isEmpty()){
                fail(String.format("%s is expected to be expired at time %d", a,time));
            }else if(expiredAlarms.size()>1){
                fail(String.format("Only %s is expected to be expired at time %d but %s are also expired", a,time,expiredAlarms));
            }else{
                Alarm actual = expiredAlarms.get(0);
                assertTrue(String.format("%s must not be expired at time %d", actual,time),actual.equals(a));
            }
            
        }

    }
}
