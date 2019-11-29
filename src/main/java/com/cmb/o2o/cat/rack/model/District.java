package com.cmb.o2o.cat.rack.model;

public class District {
    private Integer id;

    private Integer cityId;

    private String cityName;

    private String districtName;

    private String address;

    private String openHour;

    private Integer distance;

    public District(Integer id, Integer cityId, String cityName, String districtName, String address, String openHour, Integer distance) {
        this.id = id;
        this.cityId = cityId;
        this.cityName = cityName;
        this.districtName = districtName;
        this.address = address;
        this.openHour = openHour;
        this.distance = distance;
    }

    public District() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour == null ? null : openHour.trim();
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}