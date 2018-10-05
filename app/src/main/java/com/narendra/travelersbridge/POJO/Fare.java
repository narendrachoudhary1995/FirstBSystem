
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fare {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("BaseFare")
    @Expose
    private Double baseFare;
    @SerializedName("Tax")
    @Expose
    private Double tax;
    @SerializedName("TaxBreakup")
    @Expose
    private List<TaxBreakup> taxBreakup = null;
    @SerializedName("YQTax")
    @Expose
    private Integer yQTax;
    @SerializedName("AdditionalTxnFeeOfrd")
    @Expose
    private Double additionalTxnFeeOfrd;
    @SerializedName("AdditionalTxnFeePub")
    @Expose
    private Double additionalTxnFeePub;
    @SerializedName("PGCharge")
    @Expose
    private Double pGCharge;
    @SerializedName("OtherCharges")
    @Expose
    private Double otherCharges;
    @SerializedName("ChargeBU")
    @Expose
    private List<ChargeBU> chargeBU = null;
    @SerializedName("Discount")
    @Expose
    private Double discount;
    @SerializedName("PublishedFare")
    @Expose
    private Double publishedFare;
    @SerializedName("CommissionEarned")
    @Expose
    private Double commissionEarned;
    @SerializedName("PLBEarned")
    @Expose
    private Double pLBEarned;
    @SerializedName("IncentiveEarned")
    @Expose
    private Double incentiveEarned;
    @SerializedName("OfferedFare")
    @Expose
    private Double offeredFare;
    @SerializedName("TdsOnCommission")
    @Expose
    private Double tdsOnCommission;
    @SerializedName("TdsOnPLB")
    @Expose
    private Double tdsOnPLB;
    @SerializedName("TdsOnIncentive")
    @Expose
    private Double tdsOnIncentive;
    @SerializedName("ServiceFee")
    @Expose
    private Double serviceFee;
    @SerializedName("TotalBaggageCharges")
    @Expose
    private Double totalBaggageCharges;
    @SerializedName("TotalMealCharges")
    @Expose
    private Double totalMealCharges;
    @SerializedName("TotalSeatCharges")
    @Expose
    private Double totalSeatCharges;
    @SerializedName("TotalSpecialServiceCharges")
    @Expose
    private Double totalSpecialServiceCharges;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public List<TaxBreakup> getTaxBreakup() {
        return taxBreakup;
    }

    public void setTaxBreakup(List<TaxBreakup> taxBreakup) {
        this.taxBreakup = taxBreakup;
    }

    public Integer getYQTax() {
        return yQTax;
    }

    public void setYQTax(Integer yQTax) {
        this.yQTax = yQTax;
    }

    public Double getAdditionalTxnFeeOfrd() {
        return additionalTxnFeeOfrd;
    }

    public void setAdditionalTxnFeeOfrd(Double additionalTxnFeeOfrd) {
        this.additionalTxnFeeOfrd = additionalTxnFeeOfrd;
    }

    public Double getAdditionalTxnFeePub() {
        return additionalTxnFeePub;
    }

    public void setAdditionalTxnFeePub(Double additionalTxnFeePub) {
        this.additionalTxnFeePub = additionalTxnFeePub;
    }

    public Double getPGCharge() {
        return pGCharge;
    }

    public void setPGCharge(Double pGCharge) {
        this.pGCharge = pGCharge;
    }

    public Double getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        this.otherCharges = otherCharges;
    }

    public List<ChargeBU> getChargeBU() {
        return chargeBU;
    }

    public void setChargeBU(List<ChargeBU> chargeBU) {
        this.chargeBU = chargeBU;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getPublishedFare() {
        return publishedFare;
    }

    public void setPublishedFare(Double publishedFare) {
        this.publishedFare = publishedFare;
    }

    public Double getCommissionEarned() {
        return commissionEarned;
    }

    public void setCommissionEarned(Double commissionEarned) {
        this.commissionEarned = commissionEarned;
    }

    public Double getPLBEarned() {
        return pLBEarned;
    }

    public void setPLBEarned(Double pLBEarned) {
        this.pLBEarned = pLBEarned;
    }

    public Double getIncentiveEarned() {
        return incentiveEarned;
    }

    public void setIncentiveEarned(Double incentiveEarned) {
        this.incentiveEarned = incentiveEarned;
    }

    public Double getOfferedFare() {
        return offeredFare;
    }

    public void setOfferedFare(Double offeredFare) {
        this.offeredFare = offeredFare;
    }

    public Double getTdsOnCommission() {
        return tdsOnCommission;
    }

    public void setTdsOnCommission(Double tdsOnCommission) {
        this.tdsOnCommission = tdsOnCommission;
    }

    public Double getTdsOnPLB() {
        return tdsOnPLB;
    }

    public void setTdsOnPLB(Double tdsOnPLB) {
        this.tdsOnPLB = tdsOnPLB;
    }

    public Double getTdsOnIncentive() {
        return tdsOnIncentive;
    }

    public void setTdsOnIncentive(Double tdsOnIncentive) {
        this.tdsOnIncentive = tdsOnIncentive;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getTotalBaggageCharges() {
        return totalBaggageCharges;
    }

    public void setTotalBaggageCharges(Double totalBaggageCharges) {
        this.totalBaggageCharges = totalBaggageCharges;
    }

    public Double getTotalMealCharges() {
        return totalMealCharges;
    }

    public void setTotalMealCharges(Double totalMealCharges) {
        this.totalMealCharges = totalMealCharges;
    }

    public Double getTotalSeatCharges() {
        return totalSeatCharges;
    }

    public void setTotalSeatCharges(Double totalSeatCharges) {
        this.totalSeatCharges = totalSeatCharges;
    }

    public Double getTotalSpecialServiceCharges() {
        return totalSpecialServiceCharges;
    }

    public void setTotalSpecialServiceCharges(Double totalSpecialServiceCharges) {
        this.totalSpecialServiceCharges = totalSpecialServiceCharges;
    }

}
