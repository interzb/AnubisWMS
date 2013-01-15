package com.inventory.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventory.dao.BaseDao;
import com.inventory.dao.StockDao;
import com.inventory.dao.TransferDao;
import com.inventory.model.BaseVO;
import com.inventory.model.Branch;
import com.inventory.model.PageView;
import com.inventory.model.QueryResult;
import com.inventory.model.Stock;
import com.inventory.model.Transfer;
import com.inventory.util.Constants;
import com.inventory.util.ValidationUtil;

@Controller
public class TransferController {
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private StockDao stockDao;

	@Autowired
	private TransferDao transferDao;

	/**
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping("/newTransfer.do")
	public String newTransferEntry(HttpServletRequest request, ModelMap map) {
		List<BaseVO> branchs = baseDao.queryAll(Branch.class.getName());
		map.put("branchs", branchs);
		return "newTransfer";
	}

	@RequestMapping("/productsInBranch.do")
	@ResponseBody
	public String getDistinctProductInABranch(HttpServletRequest request,
			ModelMap map,
			@RequestParam(value = "branchId", required = false) Long branchId) {
		try {
			String result = stockDao.productInBranch(branchId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	@RequestMapping("/newTransferDo.do")
	@ResponseBody
	public String newTransferDo(
			HttpServletRequest request,
			ModelMap map,
			@RequestParam(value = "quantity", required = false) Long quantity,
			@RequestParam(value = "originalBranch", required = false) Long originalBranchId,
			@RequestParam(value = "destinationBranch", required = false) Long destinationBranchId,
			@RequestParam(value = "fromStockId", required = false) Long fromStockId) {

		try {
			Transfer t = new Transfer();
			t.setCreattime(ValidationUtil.sdf.format(new Date()));
			t.setDestinationBranchId(destinationBranchId);
			t.setFromBranchId(originalBranchId);
			t.setFromStockid(fromStockId);
			t.setQuantity(quantity);
			t.setStatus(0l);// new transfer;

			baseDao.addVO(t);
			return "Transfer has been made Sucessfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Failed,Please try again";
		}
	}

	@RequestMapping("/listTransfer.do")
	public String listStock(
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) Integer currentPage,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "fromBranchId", required = false) Long fromBranchId,
			@RequestParam(value = "toBranchId", required = false) Long toBranchId,
			@RequestParam(value = "transferId", required = false) Long transferId,
			ModelMap map

	) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;
		if (fromBranchId == null) {
			fromBranchId = 0L;
		}
		if (transferId == null) {
			transferId = 0L;
		}
		if (fromBranchId == null) {
			fromBranchId = 0L;
		}

		QueryResult<Transfer> qr = transferDao.queryTransfer(startDate,
				endDate, fromBranchId, toBranchId, transferId,
				Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<Transfer>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("transfers", pv);

		List<BaseVO> branchs = baseDao.queryAll(Branch.class.getName());
		map.put("branchs", branchs);

		return "listTransfer";
	}

	@RequestMapping("/denyTransfer.do")
	public String denyTransfer(
			HttpServletRequest request,
			ModelMap map,
			@RequestParam(value = "transferId", required = false) Long transferId

	) {

		try {
			Transfer t = new Transfer();
			t.setId(transferId);
			t =(Transfer) baseDao.getVOById(t);
			t.setStatus(-1L);

			baseDao.updateVO(t);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			 
		}
		return listStock(request, null, "", "", 0L, 0L, 0L, map);
	}
	/**
	 * 
	 * @param request
	 * @param map
	 * @param transferId
	 * @return
	 */
	@RequestMapping("/acceptTransfer.do")
	public String acceptTransfer(
			HttpServletRequest request,
			ModelMap map,
			@RequestParam(value = "transferId", required = false) Long transferId){
			
		Transfer t = transferDao.queryById(transferId);
		if(t!=null){
			if(t.getStocks() >= t.getQuantity()) {
				
				//remove stock from original branch's stock
				Stock originalStock = new Stock();
				originalStock.setId(t.getFromStockid());
				originalStock = (Stock)baseDao.getVOById(originalStock);
				originalStock.setQuantity(originalStock.getQuantity()-t.getQuantity());  
				baseDao.updateVO(originalStock);
				
				//add into destination stock
				
				Stock stock = new Stock();
				stock.setBranchId(t.getDestinationBranchId());
				stock.setExpiration(t.getExpiration());
				stock.setProductId(t.getProductId());
				stock.setQuantity(t.getQuantity());
				stock.setTs(ValidationUtil.sdf.format(new Date()));
				
				baseDao.addVO(stock);
				
				//set the status of the transfer
				
				Transfer tt = new Transfer();
				tt.setId(t.getId());
				tt = (Transfer)baseDao.getVOById(tt);
				tt.setStatus(1L);
				baseDao.updateVO(tt);
				
				
			} else {
				System.err.println("No enough stock for this transfer..");
				
				Transfer tt = new Transfer();
				tt.setId(t.getId());
				tt = (Transfer)baseDao.getVOById(tt);
				tt.setStatus(-2L);
				baseDao.updateVO(tt);
				
			}
		}
		return listStock(request, null, "", "", 0L, 0L, 0L, map);
	}
}
