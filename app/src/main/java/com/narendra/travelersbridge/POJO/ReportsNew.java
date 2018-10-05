
package com.narendra.travelersbridge.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportsNew {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("pid")
    @Expose
    private Integer pid;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("service_id")
    @Expose
    private Integer serviceId;
    @SerializedName("operator")
    @Expose
    private String operator;
    @SerializedName("admintds")
    @Expose
    private String admintds;
    @SerializedName("atds")
    @Expose
    private String atds;
    @SerializedName("dtds")
    @Expose
    private String dtds;
    @SerializedName("stds")
    @Expose
    private Integer stds;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("admin_beforebalance")
    @Expose
    private String adminBeforebalance;
    @SerializedName("admin_afterbalance")
    @Expose
    private String adminAfterbalance;
    @SerializedName("agent_afterbalance")
    @Expose
    private String agentAfterbalance;
    @SerializedName("agent_beforebalance")
    @Expose
    private String agentBeforebalance;
    @SerializedName("adebit")
    @Expose
    private Double adebit;
    @SerializedName("total_after_bal")
    @Expose
    private String totalAfterBal;
    @SerializedName("distributor_beforebalance")
    @Expose
    private String distributorBeforebalance;
    @SerializedName("distributor_afterbalance")
    @Expose
    private String distributorAfterbalance;
    @SerializedName("subdistributor_beforebalance")
    @Expose
    private Double subdistributorBeforebalance;
    @SerializedName("subdistributor_afterbalance")
    @Expose
    private Integer subdistributorAfterbalance;
    @SerializedName("admin_commission")
    @Expose
    private String adminCommission;
    @SerializedName("agent_commission")
    @Expose
    private String agentCommission;
    @SerializedName("distributor_commission")
    @Expose
    private Integer distributorCommission;
    @SerializedName("subdistributor_commission")
    @Expose
    private String subdistributorCommission;
    @SerializedName("trackingID")
    @Expose
    private String trackingID;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("statusmsg")
    @Expose
    private String statusmsg;
    @SerializedName("lastUpdated")
    @Expose
    private String lastUpdated;
    @SerializedName("ip_address")
    @Expose
    private String ipAddress;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Object getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAdmintds() {
        return admintds;
    }

    public void setAdmintds(String admintds) {
        this.admintds = admintds;
    }

    public String getAtds() {
        return atds;
    }

    public void setAtds(String atds) {
        this.atds = atds;
    }

    public String getDtds() {
        return dtds;
    }

    public void setDtds(String dtds) {
        this.dtds = dtds;
    }

    public Integer getStds() {
        return stds;
    }

    public void setStds(Integer stds) {
        this.stds = stds;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAdminBeforebalance() {
        return adminBeforebalance;
    }

    public void setAdminBeforebalance(String adminBeforebalance) {
        this.adminBeforebalance = adminBeforebalance;
    }

    public String getAdminAfterbalance() {
        return adminAfterbalance;
    }

    public void setAdminAfterbalance(String adminAfterbalance) {
        this.adminAfterbalance = adminAfterbalance;
    }

    public String getAgentAfterbalance() {
        return agentAfterbalance;
    }

    public void setAgentAfterbalance(String agentAfterbalance) {
        this.agentAfterbalance = agentAfterbalance;
    }

    public String getAgentBeforebalance() {
        return agentBeforebalance;
    }

    public void setAgentBeforebalance(String agentBeforebalance) {
        this.agentBeforebalance = agentBeforebalance;
    }

    public Double getAdebit() {
        return adebit;
    }

    public void setAdebit(Double adebit) {
        this.adebit = adebit;
    }

    public String getTotalAfterBal() {
        return totalAfterBal;
    }

    public void setTotalAfterBal(String totalAfterBal) {
        this.totalAfterBal = totalAfterBal;
    }

    public String getDistributorBeforebalance() {
        return distributorBeforebalance;
    }

    public void setDistributorBeforebalance(String distributorBeforebalance) {
        this.distributorBeforebalance = distributorBeforebalance;
    }

    public String getDistributorAfterbalance() {
        return distributorAfterbalance;
    }

    public void setDistributorAfterbalance(String distributorAfterbalance) {
        this.distributorAfterbalance = distributorAfterbalance;
    }

    public Double getSubdistributorBeforebalance() {
        return subdistributorBeforebalance;
    }

    public void setSubdistributorBeforebalance(Double subdistributorBeforebalance) {
        this.subdistributorBeforebalance = subdistributorBeforebalance;
    }

    public Integer getSubdistributorAfterbalance() {
        return subdistributorAfterbalance;
    }

    public void setSubdistributorAfterbalance(Integer subdistributorAfterbalance) {
        this.subdistributorAfterbalance = subdistributorAfterbalance;
    }

    public String getAdminCommission() {
        return adminCommission;
    }

    public void setAdminCommission(String adminCommission) {
        this.adminCommission = adminCommission;
    }

    public String getAgentCommission() {
        return agentCommission;
    }

    public void setAgentCommission(String agentCommission) {
        this.agentCommission = agentCommission;
    }

    public Integer getDistributorCommission() {
        return distributorCommission;
    }

    public void setDistributorCommission(Integer distributorCommission) {
        this.distributorCommission = distributorCommission;
    }

    public String getSubdistributorCommission() {
        return subdistributorCommission;
    }

    public void setSubdistributorCommission(String subdistributorCommission) {
        this.subdistributorCommission = subdistributorCommission;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
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

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

}
