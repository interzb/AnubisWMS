package com.inventory.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.inventory.dao.BaseDao;

@Controller
public class ImExportController {

	@Autowired
	private BaseDao baseDAO;

	 
	@RequestMapping("/productExport.do")
	public void exportProduct(HttpServletResponse response) {
		String filePath = baseDAO.export();
		if (filePath != null) {
			try {
				response.setContentType("application/csv");
				BufferedReader reader = new BufferedReader(new FileReader(
						filePath));
				response.setHeader("Content-Disposition", "attachment; filename=product.csv");
				String line = reader.readLine();
				PrintWriter print = new PrintWriter(response.getOutputStream());
				while (line != null) {
					print.write(line);
					print.write("\r\n");
					line = reader.readLine();
				}
				
				print.flush();
				print.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}  

	}

}
