package com.repitch.towerpower.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationInfo {

    /**
     * If the request is successful, ok is returned. Otherwise error is returned
     */
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * Any additional information from the server is returned here
     */
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * This represents the remaining balance on the API token.
     * Requests that return "error" are not charged and do not affect balance
     */
    @SerializedName("balance")
    @Expose
    private Integer balance;

    /**
     * This represents the remaining balance of device slots. Requests that return "error" are
     * not charged and do not affect balance. If -1 is returned, then observe it as an error
     * while calculating slots balance. This element will only exist if you are on a device plan.
     */
    @SerializedName("balance_slots")
    @Expose
    private Integer balanceSlots;

    @SerializedName("lat")
    @Expose
    private Double lat;

    @SerializedName("lon")
    @Expose
    private Double lon;

    /**
     * The accuracy of the position is returned in meters
     */
    @SerializedName("accuracy")
    @Expose
    private Integer accuracy;

    /**
     * The physical address of the location
     */
    @SerializedName("address")
    @Expose
    private String address;

    /**
     * The physical address of the location broken into sub-components
     */
    @SerializedName("address_details")
    @Expose
    private AddressDetails addressDetails;

    /**
     * Shown when the location is based on a single measurement or those older
     * than 90 days or is an LAC fallback.
     */
    @SerializedName("aged")
    @Expose
    private String aged;

    /**
     * Shown when the location is based on a fallback. Possible options include ipf, lacf, scf
     */
    @SerializedName("fallback")
    @Expose
    private String fallback;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSuccessful() {
        return "ok".equals(status);
    }

    @Override
    public String toString() {
        return "status: " + status + "\n" +
                "message: " + message + "\n" +
                "balance: " + balance + "\n" +
                "lat: " + lat + " lon: " + lon + "\n" +
                "accuracy: " + accuracy + "\n" +
                "address: " + address + "\n" +
                "aged: " + aged + "\n" +
                "fallback: " + fallback + "\n";
    }
}