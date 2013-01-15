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
import com.inventory.model.Family;
import com.inventory.model.PageView;
import com.inventory.model.QueryResult;
import com.inventory.util.Constants;
import com.inventory.util.ValidationUtil;
 
@Controller
public class FamilyController {

	@Autowired
	private BaseDao baseDAO;

	@RequestMapping("/familyList.do")
	public String listFamily(HttpServletRequest request,
			@RequestParam(value="currentPage",required=false) Integer currentPage) {
		if(currentPage==null) {
			currentPage = 1;
		}  
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;
		
		QueryResult<BaseVO> qr = baseDAO.queryVOBySQL("", Family.class.getName(), Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<BaseVO>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("Families", pv);
		return "listFamily";
	}
	
	
	
	@RequestMapping("/addFamily.do")
	public String addFamily(@RequestParam("name") String name,
			@RequestParam("desc") String desc,

			ModelMap map) {

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (!ValidationUtil.isValidString(desc)) {
			map.put("Desc_Error", "Description is required");

			valid = false;
		}

		if (valid) {
			Family b = new Family();
			b.setName(name);
			b.setDesc(desc);
			baseDAO.addVO(b);

			map.put("Final_Message", "New record has been added Successfully");
		}
		return "addFamily";
	}
	
	
	@RequestMapping("/updateFamily.do")
	public String updateFamilyEntry(HttpServletRequest request,
			@RequestParam(value="id",required=true) Long id) {
		if(id==null) {
			return "listFamily";
		} 
		Family p = new Family();
		p.setId(id);
		BaseVO b = baseDAO.getVOById(p);
		 
		request.setAttribute("Family", b);
		return "updateFamily";
	}
	
	@RequestMapping("/deleteFamily.do")
	public String deleteFamily(HttpServletRequest request,
			@RequestParam(value="ids",required=true) String ids) {
		if(ids==null) {
			return "listFamily";
		} 
		System.out.println("Delete ids = "+ids);
		String[] idArray = ids.trim().split(",");
		for(String id : idArray){
			Family p = new Family();
			p.setId(Long.parseLong(id));
			baseDAO.deleteVOById(p);
		}
		return listFamily(request, 1);
	}
	
	@RequestMapping("/updateFamilyDo.do")
	public String updateFamilyDo(@RequestParam("name") String name,
			@RequestParam("desc") String desc,
			@RequestParam("id") Long id,
			ModelMap map,HttpServletRequest request) {

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (!ValidationUtil.isValidString(desc)) {
			map.put("Desc_Error", "Description is required");

			valid = false;
		}
		if (valid) {
			Family p = new Family();
			p.setId(id);
			p.setDesc(desc);
			p.setName(name);
		 
			baseDAO.updateVO(p);

			map.put("Final_Message", "New record has been updated Successfully");
			return listFamily(request, 1);
		} else {
			return updateFamilyEntry(request, id);
			
		}
	}
}