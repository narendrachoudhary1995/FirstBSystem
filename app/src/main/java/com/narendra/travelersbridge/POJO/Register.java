
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Register {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("domestic")
    @Expose
    private List<Domestic> domestic = null;
    @SerializedName("international")
    @Expose
    private List<International> international = null;


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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Domestic> getDomestic() {
        return domestic;
    }

    public void setDomestic(List<Domestic> domestic) {
        this.domestic = domestic;
    }

    public List<International> getInternational() {
        return international;
    }

    public void setInternational(List<International> international) {
        this.international = international;
    }
}