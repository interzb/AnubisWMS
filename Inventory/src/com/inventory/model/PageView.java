package com.inventory.model;

import java.util.List;

import com.inventory.util.Constants;

public class PageView<T> {

	private List<T> records;

	private PageIndex pageindex;

	private long totalpage = 1;

	private int maxresult = Constants.PAGE_SIZE;

	private int currentpage = 1;
	
	private long totalrecord;

	 
	public int getFirstResult() {
		return (this.currentpage - 1) * this.maxresult;
	}

 
	public PageView() {
	}

	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}

	public void setQueryResult(QueryResult<T> qr) {
		setTotalrecord(qr.getTotalrecord());
		setRecords(qr.getResultlist());
	}

	public long getTotalrecord() {
		return totalrecord;
	}

	public void setTotalrecord(long totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord % this.maxresult == 0 ? this.totalrecord
				/ this.maxresult : this.totalrecord / this.maxresult + 1);
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public PageIndex getPageindex() {
		return pageindex;
	}

	public long getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(long totalpage) {
		this.totalpage = totalpage;
		this.pageindex = PageIndex.getPageIndex(Constants.PAGE_VIEW_RANGE, currentpage,
				totalpage);
	}

	public int getMaxresult() {
		return maxresult;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
}