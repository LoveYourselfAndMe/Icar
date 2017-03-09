package com.cheyifu.icar.tabHome.model;

/**
 * Created by Administrator on 2017/2/27.
 */
public class StopRecordsBean {
    public String parkingName  ;
    public String inTime  ;
    public String plate  ;
    public int outTime  ;

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }
}
