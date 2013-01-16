package com.inventory.model;

import java.math.BigDecimal;

/**
 * 
 * @author bzeng
 * 
 */
public class Product extends BaseVO {

	private String name;
	private Long sku;

	private String barcode;
	private Integer delivery_type;

	private String unit_of_measurement;
	private String status; // = Enum ( 'activo', 'inactivo ).

	private BigDecimal client_cost;

	private String division;
	private String group;
	private String category;
	private String subcategory;
	private String package_policy;
	private Long provider_id;
	private BigDecimal client_price;
	private BigDecimal client_tax;
	private String modified_flag;
	//private Long id_shop;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSku() {
		return sku;
	}

	public void setSku(Long sku) {
		this.sku = sku;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getDelivery_type() {
		return delivery_type;
	}

	public void setDelivery_type(Integer delivery_type) {
		this.delivery_type = delivery_type;
	}

	public String getUnit_of_measurement() {
		return unit_of_measurement;
	}

	public void setUnit_of_measurement(String unit_of_measurement) {
		this.unit_of_measurement = unit_of_measurement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	 
	public BigDecimal getClient_cost() {
		return client_cost;
	}

	public void setClient_cost(BigDecimal client_cost) {
		this.client_cost = client_cost;
	}

	public BigDecimal getClient_price() {
		return client_price;
	}

	public void setClient_price(BigDecimal client_price) {
		this.client_price = client_price;
	}

	public BigDecimal getClient_tax() {
		return client_tax;
	}

	public void setClient_tax(BigDecimal client_tax) {
		this.client_tax = client_tax;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getPackage_policy() {
		return package_policy;
	}

	public void setPackage_policy(String package_policy) {
		this.package_policy = package_policy;
	}

	public Long getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(Long provider_id) {
		this.provider_id = provider_id;
	}

	 

	public String getModified_flag() {
		return modified_flag;
	}

	public void setModified_flag(String modified_flag) {
		this.modified_flag = modified_flag;
	}

	 

}
