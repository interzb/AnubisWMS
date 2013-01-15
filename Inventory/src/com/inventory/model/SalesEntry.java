package com.inventory.model;

import java.math.BigDecimal;

public class SalesEntry extends BaseVO{
	private long salesId;
	private long productId;
	private long quantity;
	private BigDecimal price;
	private BigDecimal amount;
	private String productName;
	public long getSalesId() {
		return salesId;
	}
	public void setSalesId(long salesId) {
		this.salesId = salesId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return new BigDecimal( quantity*this.price.doubleValue());
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	 
}
