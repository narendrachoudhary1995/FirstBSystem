
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

   /* @SerializedName("ResultIndex")
    @Expose
    private String resultIndex;
    @SerializedName("Source")
    @Expose
    private Integer source;
    @SerializedName("IsLCC")
    @Expose
    private Boolean isLCC;
    @SerializedName("IsRefundable")
    @Expose
    private Boolean isRefundable;
    @SerializedName("GSTAllowed")
    @Expose
    private Boolean gSTAllowed;
    @SerializedName("IsGSTMandatory")
    @Expose
    private Boolean isGSTMandatory;
    @SerializedName("AirlineRemark")
    @Expose
    private String airlineRemark;*/
    @SerializedName("Fare")
    @Expose
    private Fare fare;
    /*@SerializedName("FareBreakdown")
    @Expose
    private List<FareBreakdown> fareBreakdown = null;*/
    @SerializedName("Segments")
    @Expose
    private List<List<Segment>> segments = null;
    /*@SerializedName("LastTicketDate")
    @Expose
    private String lastTicketDate;
    @SerializedName("TicketAdvisory")
    @Expose
    private String ticketAdvisory;
    @SerializedName("FareRules")
    @Expose
    private List<FareRule> fareRules = null;
    @SerializedName("AirlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("ValidatingAirline")
    @Expose
    private String validatingAirline;*/

   /* public String getResultIndex() {
        return resultIndex;
    }

    public void setResultIndex(String resultIndex) {
        this.resultIndex = resultIndex;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Boolean getIsLCC() {
        return isLCC;
    }

    public void setIsLCC(Boolean isLCC) {
        this.isLCC = isLCC;
    }

    public Boolean getIsRefundable() {
        return isRefundable;
    }

    public void setIsRefundable(Boolean isRefundable) {
        this.isRefundable = isRefundable;
    }

    public Boolean getGSTAllowed() {
        return gSTAllowed;
    }

    public void setGSTAllowed(Boolean gSTAllowed) {
        this.gSTAllowed = gSTAllowed;
    }

    public Boolean getIsGSTMandatory() {
        return isGSTMandatory;
    }

    public void setIsGSTMandatory(Boolean isGSTMandatory) {
        this.isGSTMandatory = isGSTMandatory;
    }

    public String getAirlineRemark() {
        return airlineRemark;
    }

    public void setAirlineRemark(String airlineRemark) {
        this.airlineRemark = airlineRemark;
    }
*/
    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }
/*
    public List<FareBreakdown> getFareBreakdown() {
        return fareBreakdown;
    }

    public void setFareBreakdown(List<FareBreakdown> fareBreakdown) {
        this.fareBreakdown = fareBreakdown;
    }*/

    public List<List<Segment>> getSegments() {
        return segments;
    }

    public void setSegments(List<List<Segment>> segments) {
        this.segments = segments;
    }

  /*  public Object getLastTicketDate() {
        return lastTicketDate;
    }

    public void setLastTicketDate(String lastTicketDate) {
        this.lastTicketDate = lastTicketDate;
    }

    public Object getTicketAdvisory() {
        return ticketAdvisory;
    }

    public void setTicketAdvisory(String ticketAdvisory) {
        this.ticketAdvisory = ticketAdvisory;
    }

    public List<FareRule> getFareRules() {
        return fareRules;
    }

    public void setFareRules(List<FareRule> fareRules) {
        this.fareRules = fareRules;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getValidatingAirline() {
        return validatingAirline;
    }

    public void setValidatingAirline(String validatingAirline) {
        this.validatingAirline = validatingAirline;
    }*/

}
