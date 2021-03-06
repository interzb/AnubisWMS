package com.inventory.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.model.BaseVO;
import com.inventory.model.Branch;
import com.inventory.model.PageView;
import com.inventory.model.QueryResult;
import com.inventory.model.SalesEntry;
import com.inventory.util.Constants;

@Controller
public class SalesController {
	@Autowired
	private BaseDao baseDao;
	@Autowired
	private SalesDao salesDao;
	@RequestMapping("/listSales.do")
	public String listStock(
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "groupByProduct", required = false) boolean groupByProduct,
			@RequestParam(value = "branchId", required = false) Long branchId,
			 
			ModelMap map

	) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;
		if (branchId == null) {
			branchId = 0L;
		}
		 

		QueryResult<SalesEntry> qr = salesDao.querySale(startDate, endDate, branchId, groupByProduct, Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<SalesEntry>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("salesEntries", pv);

		List<BaseVO> branchs = baseDao.queryAll(Branch.class.getName());
		map.put("branchs", branchs);

		return "listSales";
	}

	 
}
