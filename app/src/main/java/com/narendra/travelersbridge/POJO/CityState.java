package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityState {
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("state")
    @Expose
    private List<String> StateName;//admin/state

    @SerializedName("citylist")

    @Expose
    private List<String> CityList;//admin/city?state=Rajasthan

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<String> getStateName() {
        return StateName;
    }

    public void setStateName(List<String> stateName) {
        StateName = stateName;
    }

    public List<String> getCityList() {
        return CityList;
    }

    public void setCityList(List<String> cityList) {
        CityList = cityList;
    }

}