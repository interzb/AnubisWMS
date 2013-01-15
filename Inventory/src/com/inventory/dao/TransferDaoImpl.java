package com.inventory.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.TransferHandler.TransferSupport;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.inventory.model.QueryResult;
import com.inventory.model.Stock;
import com.inventory.model.Transfer;

public class TransferDaoImpl implements TransferDao {

	private SessionFactory sessionFactory;

	public Transfer queryById(Long tranferId) {
		Session session = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		QueryResult<Transfer> result = new QueryResult<Transfer>();
		List<Transfer> transfers = new ArrayList<Transfer>();
		result.setResultlist(transfers);
		try {
			session = sessionFactory.openSession();
			con = session.connection();
			String condition = (" AND t.id  = " + tranferId);

			String sql = "SELECT t.*,s.expiration AS expiration,s.quantity AS stocks,p.id as productId,p.name AS productName,b1.name AS fromBranchName ,b2.name AS toBranchName FROM transfer t INNER JOIN stock s ON t.fromStockid = s.id INNER JOIN product p ON p.id = s.productId INNER JOIN branch b1 ON b1.id = t.fromBranchId INNER JOIN branch b2 ON b2.id = t.destinationBranchId WHERE 1=1   "
					+ condition;

			stmt = con.createStatement();
			System.out.println(sql);
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Transfer t = new Transfer();
				t.setCreattime(rs.getString("creattime"));
				t.setFromBranchId(rs.getLong("fromBranchId"));
				t.setExpiration(rs.getString("expiration"));
				t.setDestinationBranchId(rs.getLong("destinationBranchId"));
				t.setFromBranchName(rs.getString("fromBranchName"));
				t.setFromStockid(rs.getLong("fromStockid"));
				t.setId(rs.getLong("id"));
				t.setProductName(rs.getString("productName"));
				t.setQuantity(rs.getLong("quantity"));
				t.setStatus(rs.getLong("status"));
				t.setStocks(rs.getLong("stocks"));
				t.setToBranchName(rs.getString("toBranchName"));
				t.setProductId(rs.getLong("productId"));
				
				transfers.add(t);

				return t;
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
		return null;
	}

	/**
	 * 
	 */
	public QueryResult<Transfer> queryTransfer(String startDate,
			String endDate, Long fromBranchId, Long toBranchId, Long tranferId,
			int pageSize, int offset) {
		Session session = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		QueryResult<Transfer> result = new QueryResult<Transfer>();
		List<Transfer> transfers = new ArrayList<Transfer>();
		result.setResultlist(transfers);
		try {
			session = sessionFactory.openSession();
			con = session.connection();
			String condition = "";
			if (tranferId != null && tranferId > 0) {
				condition += (" AND t.id  = " + tranferId);
			} else {
				if (startDate != null && startDate.trim().length() > 0) {
					condition += (" AND t.creattime  >= '" + startDate + "'");
				}
				if (endDate != null && endDate.trim().length() > 0) {
					condition += (" AND t.creattime <= '" + endDate + "'");
				}
				if (fromBranchId != null && fromBranchId > 0) {
					condition += (" AND t.fromBranchId  = " + fromBranchId);
				}
				if (toBranchId != null && toBranchId > 0) {
					condition += (" AND t.destinationBranchId  = " + toBranchId);
				}
			}
			String sql = "SELECT COUNT(1) FROM transfer t  WHERE  1=1 "
					+ condition;
			System.out.println(sql);

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			int count = 0;
			if (rs.next()) {
				count = rs.getInt(1);
			}

			if (count > 0) {
				sql = "SELECT t.*,s.expiration AS expiration,s.quantity AS stocks,p.name AS productName,b1.name AS fromBranchName ,b2.name AS toBranchName FROM transfer t INNER JOIN stock s ON t.fromStockid = s.id INNER JOIN product p ON p.id = s.productId INNER JOIN branch b1 ON b1.id = t.fromBranchId INNER JOIN branch b2 ON b2.id = t.destinationBranchId WHERE 1=1   "
						+ condition
						+ " limit "
						+ pageSize
						+ " offset "
						+ offset;
				stmt = con.createStatement();
				System.out.println(sql);
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					Transfer t = new Transfer();
					t.setCreattime(rs.getString("creattime"));
					t.setFromBranchId(rs.getLong("fromBranchId"));
					t.setExpiration(rs.getString("expiration"));
					t.setDestinationBranchId(rs.getLong("destinationBranchId"));
					t.setFromBranchName(rs.getString("fromBranchName"));
					t.setFromStockid(rs.getLong("fromStockid"));
					t.setId(rs.getLong("id"));
					t.setProductName(rs.getString("productName"));
					t.setQuantity(rs.getLong("quantity"));
					t.setStatus(rs.getLong("status"));
					t.setStocks(rs.getLong("stocks"));
					t.setToBranchName(rs.getString("toBranchName"));

					transfers.add(t);
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
