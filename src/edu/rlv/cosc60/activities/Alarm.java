package edu.rlv.cosc60.activities;

import java.util.Objects;

/**
 *
 * @author russel
 */
public class Alarm implements Comparable<Alarm> {

    private final String name;
    private final int snooze;
    private final int time;
    private final int repeat;

    public Alarm(String name, int time, int snooze, int repeat) {
        this.name = name;
        this.snooze = snooze;
        this.time = time;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public int getSnooze() {
        return snooze;
    }

    public int getTime() {
        return time;
    }

    public int getRepeat() {
        return repeat;
    }
    
    @Override
    public int compareTo(Alarm o) {
       return this.time < o.time ? -1 : (this.time > o.time? 1:0);
    }

    @Override
    public String toString() {
        return String.format("(Name: %s,Time: %d,Snooze: %d,Repeat: %d)",
                this.name, this.time, this.snooze,this.repeat);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.snooze;
        hash = 37 * hash + this.time;
        hash = 37 * hash + this.repeat;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Alarm)) {
            return false;
        }
        final Alarm other = (Alarm) obj;
        if (this.snooze != other.snooze) {
            return false;
        }
        if (this.time != other.time) {
            return false;
        }
       
        if (this.repeat != other.repeat) {
            return false;
        }
        
        return Objects.equals(this.name, other.name);
    }

}
