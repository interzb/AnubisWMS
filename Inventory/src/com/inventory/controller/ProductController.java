package com.inventory.controller;

import java.math.BigDecimal;
import java.util.List;

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
import com.inventory.model.Product;
import com.inventory.model.QueryResult;
import com.inventory.util.Constants;
import com.inventory.util.ValidationUtil;

@Controller
public class ProductController {

	@Autowired
	private BaseDao baseDAO;

	@RequestMapping("/productList.do")
	public String listProduct(
			HttpServletRequest request,
			@RequestParam(value = "currentPage", required = false) Integer currentPage) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int offset = (currentPage - 1) * Constants.PAGE_SIZE;

		QueryResult<BaseVO> qr = baseDAO.queryVOBySQL("",
				Product.class.getName(), Constants.PAGE_SIZE, offset);

		PageView pv = new PageView<BaseVO>(Constants.PAGE_SIZE, currentPage);
		pv.setQueryResult(qr);
		request.setAttribute("Products", pv);
		return "listProduct";
	}

	@RequestMapping("/updateProduct.do")
	public String updateProductEntry(HttpServletRequest request,
			@RequestParam(value = "id", required = true) Long id) {
		if (id == null) {
			return "listProduct";
		}

		List<BaseVO> families = baseDAO.queryAll(Family.class.getName());
		request.setAttribute("Families", families);

		List<BaseVO> branches = baseDAO.queryAll(Branch.class.getName());
		request.setAttribute("Branches", branches);

		Product p = new Product();
		p.setId(id);
		BaseVO b = baseDAO.getVOById(p);

		request.setAttribute("Product", b);
		return "updateProduct";
	}

	@RequestMapping("/deleteProduct.do")
	public String deleteProduct(HttpServletRequest request,
			@RequestParam(value = "ids", required = true) String ids) {
		if (ids == null) {
			return "listProduct";
		}
		System.out.println("Delete ids = " + ids);
		String[] idArray = ids.trim().split(",");
		for (String id : idArray) {
			Product p = new Product();
			p.setId(Long.parseLong(id));
			baseDAO.deleteVOById(p);
		}
		return listProduct(request, 1);
	}

	@RequestMapping("/addProductEntry.do")
	public String addProductEntry(HttpServletRequest request) {
		List<BaseVO> families = baseDAO.queryAll(Family.class.getName());
		request.setAttribute("Families", families);

		List<BaseVO> branches = baseDAO.queryAll(Branch.class.getName());
		request.setAttribute("Branches", branches);

		return "addProduct";
	}

	@RequestMapping("/addProduct.do")
	public String addProduct(@RequestParam("name") String name,
			@RequestParam("sku") Long sku,

			@RequestParam("barcode") String barcode,
			@RequestParam("delivery_type") Integer delivery_type,
			@RequestParam("unit_of_measurement") String unit_of_measurement,
			@RequestParam("status") String status,
			@RequestParam("client_cost") BigDecimal client_cost,
			@RequestParam("division") String division,
			@RequestParam("group") String group,
			@RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory,
			@RequestParam("package_policy") String package_policy,

			@RequestParam("provider_id") Long provider_id,
			@RequestParam("client_price") BigDecimal client_price,
			@RequestParam("client_tax") BigDecimal client_tax,
			@RequestParam("modified_flag") String modified_flag,
			 

			ModelMap map, HttpServletRequest request) {
		List<BaseVO> families = baseDAO.queryAll(Family.class.getName());
		request.setAttribute("Families", families);

		List<BaseVO> branches = baseDAO.queryAll(Branch.class.getName());
		request.setAttribute("Branches", branches);

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (valid) {
			Product p = new Product();

			p.setName(name);
			p.setSku(sku);
			p.setBarcode(barcode);
			p.setCategory(category);

			p.setClient_cost(client_cost);
			p.setClient_price(client_price);
			p.setClient_tax(client_tax);
			p.setDelivery_type(delivery_type);
			p.setDivision(division);
			System.out.println("Group = " + group);
			p.setGroup(group);
			//p.setId_shop(id_shop);
			p.setModified_flag(modified_flag);
			p.setPackage_policy(package_policy);
			p.setProvider_id(provider_id);
			p.setStatus(status);
			p.setSubcategory(subcategory);
			p.setUnit_of_measurement(unit_of_measurement);

			baseDAO.addVO(p);

			map.put("Final_Message", "New record has been added Successfully");
		}
		return "addProduct";
	}

	@RequestMapping("/updateProductDo.do")
	public String updateProductDo(@RequestParam("name") String name,
			@RequestParam("sku") Long sku,

			@RequestParam("barcode") String barcode,
			@RequestParam("delivery_type") Integer delivery_type,
			@RequestParam("unit_of_measurement") String unit_of_measurement,
			@RequestParam("status") String status,
			@RequestParam("client_cost") BigDecimal client_cost,
			@RequestParam("division") String division,
			@RequestParam("group") String group,
			@RequestParam("category") String category,
			@RequestParam("subcategory") String subcategory,
			@RequestParam("package_policy") String package_policy,

			@RequestParam("provider_id") Long provider_id,
			@RequestParam("client_price") BigDecimal client_price,
			@RequestParam("client_tax") BigDecimal client_tax,
			@RequestParam("modified_flag") String modified_flag,
			 

			@RequestParam("id") Long id, ModelMap map,
			HttpServletRequest request) {

		List<BaseVO> families = baseDAO.queryAll(Family.class.getName());
		request.setAttribute("Families", families);

		List<BaseVO> branches = baseDAO.queryAll(Branch.class.getName());
		request.setAttribute("Branches", branches);

		boolean valid = true;
		if (!ValidationUtil.isValidString(name)) {
			map.put("Name_Error", "Name is required");

			valid = false;
		}

		if (valid) {
			Product p = new Product();
			p.setId(id);

			p.setName(name);
			p.setSku(sku);
			p.setBarcode(barcode);
			p.setCategory(category);

			p.setClient_cost(client_cost);
			p.setClient_price(client_price);
			p.setClient_tax(client_tax);
			p.setDelivery_type(delivery_type);
			p.setDivision(division);
			p.setGroup(group);
			//p.setId_shop(id_shop);
			p.setModified_flag(modified_flag);
			p.setPackage_policy(package_policy);
			p.setProvider_id(provider_id);
			p.setStatus(status);
			p.setSubcategory(subcategory);
			p.setUnit_of_measurement(unit_of_measurement);

			baseDAO.updateVO(p);

			map.put("Final_Message", "New record has been updated Successfully");
			return listProduct(request, 1);
		} else {
			return updateProductEntry(request, id);

		}
	}
}
