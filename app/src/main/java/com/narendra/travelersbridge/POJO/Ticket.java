
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("agent_id")
    @Expose
    private Integer agentId;
    @SerializedName("trans_id")
    @Expose
    private String transId;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("reference_id")
    @Expose
    private String referenceId;
    @SerializedName("confirmation_id")
    @Expose
    private String confirmationId;
    @SerializedName("trace_id")
    @Expose
    private String traceId;
    @SerializedName("token_id")
    @Expose
    private String tokenId;
    @SerializedName("cancel_id")
    @Expose
    private String cancelId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("pnr_number")
    @Expose
    private String pnrNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("flight_name")
    @Expose
    private String flightName;
    @SerializedName("flight_code")
    @Expose
    private String flightCode;
    @SerializedName("jdate")
    @Expose
    private String jdate;
    @SerializedName("f_name")
    @Expose
    private String fName;
    @SerializedName("result_index")
    @Expose
    private String resultIndex;
    @SerializedName("source")
    @Expose
    private Integer source;
    @SerializedName("is_lcc")
    @Expose
    private String isLcc;
    @SerializedName("source_dest")
    @Expose
    private String sourceDest;
    @SerializedName("no_of_pass")
    @Expose
    private Integer noOfPass;
    @SerializedName("base_fare")
    @Expose
    private String baseFare;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("other_tax")
    @Expose
    private String otherTax;
    @SerializedName("other_charges")
    @Expose
    private String otherCharges;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("actual_fare")
    @Expose
    private String actualFare;
    @SerializedName("agent_markup")
    @Expose
    private String agentMarkup;
    @SerializedName("margin_on")
    @Expose
    private String marginOn;
    @SerializedName("margin_amount")
    @Expose
    private String marginAmount;
    @SerializedName("admin_margin")
    @Expose
    private String adminMargin;
    @SerializedName("agent_margin")
    @Expose
    private String agentMargin;
    @SerializedName("dist_margin")
    @Expose
    private String distMargin;
    @SerializedName("admin_service_tax")
    @Expose
    private String adminServiceTax;
    @SerializedName("booking_status")
    @Expose
    private String bookingStatus;
    @SerializedName("ticket_status")
    @Expose
    private String ticketStatus;
    @SerializedName("cancel_status")
    @Expose
    private String cancelStatus;
    @SerializedName("refund_status")
    @Expose
    private String refundStatus;
    @SerializedName("refund_amount")
    @Expose
    private Double refundAmount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusmsg")
    @Expose
    private String statusmsg;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("reports_new")
    @Expose
    private ReportsNew reportsNew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Object getCancelId() {
        return cancelId;
    }

    public void setCancelId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    public void setPnrNumber(String pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getResultIndex() {
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

    public String getIsLcc() {
        return isLcc;
    }

    public void setIsLcc(String isLcc) {
        this.isLcc = isLcc;
    }

    public String getSourceDest() {
        return sourceDest;
    }

    public void setSourceDest(String sourceDest) {
        this.sourceDest = sourceDest;
    }

    public Integer getNoOfPass() {
        return noOfPass;
    }

    public void setNoOfPass(Integer noOfPass) {
        this.noOfPass = noOfPass;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getOtherTax() {
        return otherTax;
    }

    public void setOtherTax(String otherTax) {
        this.otherTax = otherTax;
    }

    public String getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getActualFare() {
        return actualFare;
    }

    public void setActualFare(String actualFare) {
        this.actualFare = actualFare;
    }

    public String getAgentMarkup() {
        return agentMarkup;
    }

    public void setAgentMarkup(String agentMarkup) {
        this.agentMarkup = agentMarkup;
    }

    public String getMarginOn() {
        return marginOn;
    }

    public void setMarginOn(String marginOn) {
        this.marginOn = marginOn;
    }

    public String getMarginAmount() {
        return marginAmount;
    }

    public void setMarginAmount(String marginAmount) {
        this.marginAmount = marginAmount;
    }

    public String getAdminMargin() {
        return adminMargin;
    }

    public void setAdminMargin(String adminMargin) {
        this.adminMargin = adminMargin;
    }

    public String getAgentMargin() {
        return agentMargin;
    }

    public void setAgentMargin(String agentMargin) {
        this.agentMargin = agentMargin;
    }

    public String getDistMargin() {
        return distMargin;
    }

    public void setDistMargin(String distMargin) {
        this.distMargin = distMargin;
    }

    public String getAdminServiceTax() {
        return adminServiceTax;
    }

    public void setAdminServiceTax(String adminServiceTax) {
        this.adminServiceTax = adminServiceTax;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public Object getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Object getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusmsg() {
        return statusmsg;
    }

    public void setStatusmsg(String statusmsg) {
        this.statusmsg = statusmsg;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ReportsNew getReportsNew() {
        return reportsNew;
    }

    public void setReportsNew(ReportsNew reportsNew) {
        this.reportsNew = reportsNew;
    }

}
