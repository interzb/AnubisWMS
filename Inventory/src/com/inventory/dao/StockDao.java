package com.inventory.dao;

import com.inventory.model.QueryResult;
import com.inventory.model.Stock;

public interface StockDao {
	public QueryResult<Stock> queryStock(String startDate,String endDate,Long branchId,Long stockId, int pageSize,int offset) ;
	public String productInBranch(Long branchId) ;
}
