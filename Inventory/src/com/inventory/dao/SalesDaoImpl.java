package com.inventory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.inventory.model.QueryResult;
import com.inventory.model.SalesEntry;

public class SalesDaoImpl implements SalesDao {
	private SessionFactory sessionFactory;

	public QueryResult<SalesEntry> querySale(String startDate, String endDate,
			Long branchId, boolean groupByProduct, int pageSize, int offset) {

		Session session = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		QueryResult<SalesEntry> result = new QueryResult<SalesEntry>();
		List<SalesEntry> entries = new ArrayList<SalesEntry>();
		result.setResultlist(entries);
		try {
			session = sessionFactory.openSession();
			con = session.connection();
			String condition = "";

			if (startDate != null && startDate.trim().length() > 0) {
				condition += (" AND creattime >= '" + startDate + "'");
			}
			if (endDate != null && endDate.trim().length() > 0) {
				condition += (" AND creattime <= '" + endDate + "'");
			}
			if (branchId != null && branchId > 0) {
				condition += (" AND s.branchId  = " + branchId);
			}

			String sql = "SELECT COUNT(1) FROM salesentry e INNER JOIN product p ON p.id = e.productId INNER JOIN sales s ON s.id = e.saleId WHERE  1=1 " + condition +" ORDER BY  p.id ";
			System.out.println(sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				sql = "SELECT e.*,p.id AS productId,p.name,p.client_price FROM salesentry e INNER JOIN product p ON p.id = e.productId INNER JOIN sales s ON s.id = e.saleId WHERE 1=1   "
						+ condition
						+ " limit "
						+ pageSize
						+ " offset "
						+ offset;
				stmt = con.createStatement();
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					SalesEntry entry = new SalesEntry();
					entry.setAmount(rs.getBigDecimal("amount"));
					entry.setQuantity(rs.getLong("quantity"));
					entry.setProductName(rs.getString("name"));
					entry.setPrice(rs.getBigDecimal("client_price"));
					entry.setProductId(rs.getLong("productId"));
					entry.setSalesId(rs.getLong("salesId"));

					entries.add(entry);
				}
			}

			result.setTotalrecord(count);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				stmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				con.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				session.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
}
