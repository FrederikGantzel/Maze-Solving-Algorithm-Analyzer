package com.utility;

public class Data {
    public int smallest;
    public int largest;
    public int [] itteDFS;
    public long [] timeDFS;
    public int [] itteEl;
    public long [] timeEl;
    public int [] ittePrims;
    public long [] timePrims;

    public void data (int smallest, int largest, int [] avgItteEl, long [] avgTimeEl,
                      int [] avgItteDFS, long [] avgTimeDFS, int [] avgIttePrims, long [] avgTimePrims){
        this.itteDFS = avgItteDFS;
        this.timeDFS = avgTimeDFS;
        this.itteEl = avgItteEl;
        this.timeEl = avgTimeEl;
        this.ittePrims = avgIttePrims;
        this.timePrims = avgTimePrims;
        this.smallest = smallest;
        this.largest = largest;
    }
}
