package com.geekym.care4u.HomeScreen.VaccineSlot;

public class CenterModel {

    private String CenterName;
    private String CenterLocation;
    private String CenterFromTime;
    private String CenterEndTime;
    private int AgeLimit;
    private String FeeType;
    private String VaccineName;
    private int Availability;
    private int Dose1;
    private int Dose2;

    public int getDose1() {
        return Dose1;
    }

    public void setDose1(int dose1) {
        Dose1 = dose1;
    }

    public int getDose2() {
        return Dose2;
    }

    public void setDose2(int dose2) {
        Dose2 = dose2;
    }

    public CenterModel() {
    }

    public CenterModel(String centerName, String centerLocation, String centerFromTime, String centerEndTime, int ageLimit, String feeType, String vaccineName, int availability, int dose1, int dose2) {
        CenterName = centerName;
        CenterLocation = centerLocation;
        CenterFromTime = centerFromTime;
        CenterEndTime = centerEndTime;
        AgeLimit = ageLimit;
        FeeType = feeType;
        VaccineName = vaccineName;
        Availability = availability;
        Dose1 = dose1;
        Dose2 = dose2;
    }

    public String getCenterName() {
        return CenterName;
    }

    public void setCenterName(String centerName) {
        CenterName = centerName;
    }

    public String getCenterLocation() {
        return CenterLocation;
    }

    public void setCenterLocation(String centerLocation) {
        CenterLocation = centerLocation;
    }

    public String getCenterFromTime() {
        return CenterFromTime;
    }

    public void setCenterFromTime(String centerFromTime) {
        CenterFromTime = centerFromTime;
    }

    public String getCenterEndTime() {
        return CenterEndTime;
    }

    public void setCenterEndTime(String centerEndTime) {
        CenterEndTime = centerEndTime;
    }

    public int getAgeLimit() {
        return AgeLimit;
    }

    public void setAgeLimit(int ageLimit) {
        AgeLimit = ageLimit;
    }

    public String getFeeType() {
        return FeeType;
    }

    public void setFeeType(String feeType) {
        FeeType = feeType;
    }

    public String getVaccineName() {
        return VaccineName;
    }

    public void setVaccineName(String vaccineName) {
        VaccineName = vaccineName;
    }

    public int getAvailability() {
        return Availability;
    }

    public void setAvailability(int availability) {
        Availability = availability;
    }

    @Override
    public String toString() {
        return "CenterModel{" +
                "CenterName='" + CenterName + '\'' +
                ", CenterLocation='" + CenterLocation + '\'' +
                ", CenterFromTime='" + CenterFromTime + '\'' +
                ", CenterEndTime='" + CenterEndTime + '\'' +
                ", AgeLimit=" + AgeLimit +
                ", FeeType='" + FeeType + '\'' +
                ", VaccineName='" + VaccineName + '\'' +
                ", Availability=" + Availability +
                '}';
    }
}
