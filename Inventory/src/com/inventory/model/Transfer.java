package com.inventory.model;

public class Transfer extends BaseVO{
	private Long fromBranchId;
	private Long destinationBranchId;
	private Long fromStockid;
	private Long quantity;
	private Long status;
	private String creattime;
	
	private String statusString;
	
	//transient
	private String fromBranchName;
	private String productName;
	private String toBranchName;
	private String expiration;
	private Long stocks;
	private Long productId;
	
	public Long getFromBranchId() {
		return fromBranchId;
	}
	public void setFromBranchId(Long fromBranchId) {
		this.fromBranchId = fromBranchId;
	}
	public Long getDestinationBranchId() {
		return destinationBranchId;
	}
	public void setDestinationBranchId(Long destinationBranchId) {
		this.destinationBranchId = destinationBranchId;
	}
	public Long getFromStockid() {
		return fromStockid;
	}
	public void setFromStockid(Long fromStockid) {
		this.fromStockid = fromStockid;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	 
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	public String getStatusString() {
		if(status==0){
			return "Waiting";
		} else if(status == 1){
			return "Received";
		} else if(status == -1){
			return "Denied";
		} else if(status == -2) {
			return "No enough stocks";
		}else {
			return "Wrong Status";
		}
	}
	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}
	public String getFromBranchName() {
		return fromBranchName;
	}
	public void setFromBranchName(String fromBranchName) {
		this.fromBranchName = fromBranchName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getToBranchName() {
		return toBranchName;
	}
	public void setToBranchName(String toBranchName) {
		this.toBranchName = toBranchName;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public Long getStocks() {
		return stocks;
	}
	public void setStocks(Long stocks) {
		this.stocks = stocks;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
	
}
