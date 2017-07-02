package com.repitch.towerpower.api;

/**
 * Created by repitch on 02.07.17.
 */
public class AddressDetails {

    /**
     * area
     * locality
     * district
     * county
     * city
     * state
     * country
     * country_code
     * postal_code
     */

    private String area;
    private String locality;
    private String district;
    private String county;
    private String city;
    private String state;
    private String country;
    private String country_code;
    private String postal_code;


    public String getArea() {
        return area;
    }

    public String getLocality() {
        return locality;
    }

    public String getDistrict() {
        return district;
    }

    public String getCounty() {
        return county;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getPostal_code() {
        return postal_code;
    }
}
