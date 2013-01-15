package com.inventory.model;

import java.util.List;

public class Sales extends BaseVO {
	private long branchId;
	private String createtime;
	private List<SalesEntry> entries;
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
 
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public List<SalesEntry> getEntries() {
		return entries;
	}
	public void setEntries(List<SalesEntry> entries) {
		this.entries = entries;
	}
	
}
