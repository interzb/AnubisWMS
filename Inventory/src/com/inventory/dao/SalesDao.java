package com.inventory.dao;

import org.hibernate.SessionFactory;

import com.inventory.model.QueryResult;
import com.inventory.model.SalesEntry;

public interface SalesDao {
	
	public QueryResult<SalesEntry> querySale(String startDate,String endDate,Long branchId,boolean groupByProduct, int pageSize,int offset) ;
	 
}
