package com.inventory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.inventory.model.QueryResult;
import com.inventory.model.Stock;
import com.inventory.util.Constants;

public class StockDaoImpl implements StockDao {
	private SessionFactory sessionFactory;

	/**
	 * Stock query
	 */
	@SuppressWarnings("deprecation")
	public QueryResult<Stock> queryStock(String startDate, String endDate,
			Long branchId, Long stockId, int pageSize, int offset) {
		Session session = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		QueryResult<Stock> result = new QueryResult<Stock>();
		List<Stock> stocks = new ArrayList<Stock>();
		result.setResultlist(stocks);
		try {
			session = sessionFactory.openSession();
			con = session.connection();
			String condition = "";
			if (stockId != null && stockId > 0) {
				condition += (" AND s.id  = " + stockId);
			} else {
				if (startDate != null && startDate.trim().length() > 0) {
					condition += (" AND s.expiration >= '" + startDate + "'");
				}
				if (endDate != null && endDate.trim().length() > 0) {
					condition += (" AND s.expiration <= '" + endDate + "'");
				}
				if (branchId != null && branchId > 0) {
					condition += (" AND s.branchId  = " + branchId);
				}
			}
			String sql = "SELECT COUNT(1) FROM Stock s WHERE  1=1 " + condition;
			System.out.println(sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				sql = "SELECT s.* , b.name AS branchName,p.name as productName FROM stock s left join branch b  ON s.branchId = b.id left join product p on s.productId = p.id WHERE 1=1   "
						+ condition
						+ " limit "
						+ pageSize
						+ " offset "
						+ offset;
				stmt = con.createStatement();
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Stock stock = new Stock();
					stock.setBranchId(rs.getLong("branchId"));
					stock.setBranchName(rs.getString("branchName"));
					stock.setExpiration(rs.getString("expiration"));
					stock.setId(rs.getLong("id"));
					stock.setProductId(rs.getLong("productId"));
					stock.setProductName(rs.getString("productName"));
					stock.setQuantity(rs.getLong("quantity"));
					stock.setTs(rs.getString("ts"));

					stocks.add(stock);
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
	/**
	 * Product in one branch
	 */
	public String productInBranch(Long branchId) {
		Session session = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String result = "";
		try {
			session = sessionFactory.openSession();
			con = session.connection();

			String sql = "SELECT s.*,p.name as productName FROM stock s INNER JOIN product p ON s.productId = p.id WHERE s.branchId ="
					+ branchId+" ORDER BY quantity DESC ";
			System.out.println(sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				long id = rs.getLong(1);
				String name = rs.getString("productName");
				String expiration = rs.getString("expiration");
				long quantity = rs.getLong("quantity");
				
				result+=(id+Constants.boundary+name+Constants.boundary+expiration+Constants.boundary+quantity+Constants.boundary);
			}
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
