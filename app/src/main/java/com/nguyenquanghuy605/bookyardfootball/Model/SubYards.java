package com.nguyenquanghuy605.bookyardfootball.Model;

public class SubYards {
    private long id;
    private long optionyard;
    private long yard;

    public SubYards(){}

    public SubYards(long id, long optionyard, long yard) {
        this.id = id;
        this.optionyard = optionyard;
        this.yard = yard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOptionyard() {
        return optionyard;
    }

    public void setOptionyard(long optionyard) {
        this.optionyard = optionyard;
    }

    public long getYard() {
        return yard;
    }

    public void setYard(long yard) {
        this.yard = yard;
    }
}
