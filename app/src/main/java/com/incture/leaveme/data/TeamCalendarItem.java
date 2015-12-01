package com.incture.leaveme.data;

/**
 * Created by Mohammed on 11/16/2015.
 */
public class TeamCalendarItem {
    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    int dp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    String name,avail;
    public TeamCalendarItem(int dp, String name, String avail){
        this.dp= dp;
        this.name=name;
        this.avail=avail;
    }
}
