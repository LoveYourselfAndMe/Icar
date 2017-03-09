package com.cheyifu.icar.tabHome.model;

/**
 * Created by Administrator on 2017/2/23.
 */
public class RecordsBean {
    public String parkingName  ;
    public String inTime  ;
    public String plate  ;
    public int lockStatus  ;

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

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }
}
