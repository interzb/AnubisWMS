package com.inventory.dao;

import com.inventory.model.QueryResult;
import com.inventory.model.Transfer;

public interface TransferDao {
	public QueryResult<Transfer> queryTransfer(String startDate,String endDate,Long fromBranchId,Long toBranchId, Long tranferId ,int pageSize,int offset) ;
	public Transfer queryById( Long tranferId ) ;
}
