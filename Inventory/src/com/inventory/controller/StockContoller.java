package com.inventory.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.dao.BaseDao;
import com.inventory.dao.StockDao;
import com.inventory.model.BaseVO;
import com.inventory.model.Branch;
import com.inventory.model.PageView;
import com.inventory.model.Product;
import com.inventory.model.QueryResult;
import com.inventory.model.Stock;
import com.inventory.util.Constants;

@Controller
public class StockContoller {
	@Autowired
	private StockDao stockDao;
	@Autowired
	private BaseDao baseDAO;

	@RequestMapping("/stockList.do")
	public String listStock(
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "branchId", required = false) Long branchId,
			@RequestParam(value = "stockId", required = false) Long stockId,
			ModelMap map

	) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;
		if(branchId==null){
			branchId = 0L;
		}
		if(stockId==null){
			stockId = 0L;
		}

		QueryResult<Stock> qr = stockDao.queryStock(startDate, endDate,branchId, stockId, Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<Stock>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("stocks", pv);
		
		List<BaseVO> branchs = baseDAO.queryAll(Branch.class.getName());
		map.put("branchs", branchs);
	 
		return "listStock";
	}

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/enterStock.do")
	public String addStock(HttpServletRequest request, ModelMap map) {
		List<BaseVO> branchs = baseDAO.queryAll(Branch.class.getName());
		map.put("branchs", branchs);

		List<BaseVO> products = baseDAO.queryAll(Product.class.getName());
		map.put("products", products);

		return "entryGoods";
	}

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/enterStockDo.do")
	public String addStockDo(HttpServletRequest request, ModelMap map) {
		long branchId = Long.parseLong(request.getParameter("branch"));
		// Loop
		for (int i = 1; i <= Constants.SIZE; i++) {
			try {
				long productId = Long.parseLong(request
						.getParameter("productId" + i));
				long quantity = Long.parseLong(request.getParameter("quantity"
						+ i));
				String expiration = request.getParameter("expiration" + i);

				Stock stock = new Stock();
				stock.setBranchId(branchId);
				stock.setExpiration(expiration);
				stock.setQuantity(quantity);
				stock.setProductId(productId);
				stock.setTs(new Date().toString());
				baseDAO.addVO(stock);

				map.put("info", "Good has been added successfully.");
			} catch (Exception e) {
				if (map.get("info") == null) {
					map.put("info", "Failed,please check you input");
				}
				e.printStackTrace();
				break;
			}
		}
		List<BaseVO> branchs = baseDAO.queryAll(Branch.class.getName());
		map.put("branchs", branchs);

		List<BaseVO> products = baseDAO.queryAll(Product.class.getName());
		map.put("products", products);

		return "entryGoods";
	}

}
