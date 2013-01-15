package com.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.dao.BaseDao;
import com.inventory.model.BaseVO;
import com.inventory.model.Branch;
import com.inventory.model.PageView;
import com.inventory.model.QueryResult;
import com.inventory.util.Constants;
import com.inventory.util.ValidationUtil;

@Controller
public class BranchController {

	@Autowired
	private BaseDao baseDAO;
	
	

	@RequestMapping("/branchList.do")
	public String listBranch(HttpServletRequest request,
			@RequestParam(value="currentPage",required=false) Integer currentPage) {
		if(currentPage==null) {
			currentPage = 1;
		}  
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;
		
		QueryResult<BaseVO> qr = baseDAO.queryVOBySQL("", Branch.class.getName(), Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<BaseVO>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("Branchs", pv);
		return "listBranch";
	}
	

	@RequestMapping("/addBranch.do")
	public String addBranch(@RequestParam("name") String name,
			@RequestParam("address") String address,

			ModelMap map) {

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (!ValidationUtil.isValidString(address)) {
			map.put("Address_Error", "Branch is required");

			valid = false;
		}

		if (valid) {
			Branch b = new Branch();
			b.setName(name);
			b.setAddress(address);
			baseDAO.addVO(b);

			map.put("Final_Message", "New record has been added Successfully");
		}
		return "addBranch";
	}
	
	
	@RequestMapping("/updateBranch.do")
	public String updateBranchEntry(HttpServletRequest request,
			@RequestParam(value="id",required=true) Long id) {
		if(id==null) {
			return "listBranch";
		} 
		Branch p = new Branch();
		p.setId(id);
		BaseVO b = baseDAO.getVOById(p);
		 
		request.setAttribute("Branch", b);
		return "updateBranch";
	}
	
	@RequestMapping("/deleteBranch.do")
	public String deleteBranch(HttpServletRequest request,
			@RequestParam(value="ids",required=true) String ids) {
		if(ids==null) {
			return "listBranch";
		} 
		System.out.println("Delete ids = "+ids);
		String[] idArray = ids.trim().split(",");
		for(String id : idArray){
			Branch p = new Branch();
			p.setId(Long.parseLong(id));
			baseDAO.deleteVOById(p);
		}
		return listBranch(request, 1);
	}
	
	@RequestMapping("/updateBranchDo.do")
	public String updateBranchDo(@RequestParam("name") String name,
			@RequestParam("address") String address,
			@RequestParam("id") Long id,
			ModelMap map,HttpServletRequest request) {

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (!ValidationUtil.isValidString(address)) {
			map.put("Family_Error", "Address is required");

			valid = false;
		}
		if (valid) {
			Branch p = new Branch();
			p.setId(id);
			p.setAddress(address);
			p.setName(name);
		 
			baseDAO.updateVO(p);

			map.put("Final_Message", "New record has been updated Successfully");
			return listBranch(request, 1);
		} else {
			return updateBranchEntry(request, id);
			
		}
	}
}
