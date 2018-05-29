package com.nguyenquanghuy605.bookyardfootball.Model;

public class Time {
    private String timestart;
    private String timeend;

    public Time(String timestart, String timeend) {
        this.timestart = timestart;
        this.timeend = timeend;
    }

    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }


}
