package com.nguyenquanghuy605.bookyardfootball.Model;

public class PriceTime {

    private long id;
    private long price;
    private long timestart;
    private long timeend;
    private long idyard;

    public PriceTime(){}

    public PriceTime(long id, long price, long timestart, long timeend, long idyard) {
        this.id = id;
        this.price = price;
        this.timestart = timestart;
        this.timeend = timeend;
        this.idyard = idyard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }

    public long getTimeend() {
        return timeend;
    }

    public void setTimeend(long timeend) {
        this.timeend = timeend;
    }

    public long getIdyard() {
        return idyard;
    }

    public void setIdyard(long idyard) {
        this.idyard = idyard;
    }
}
