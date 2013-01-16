package com.inventory.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.inventory.model.BaseVO;
import com.inventory.model.QueryResult;

public class BaseDaoImpl implements BaseDao {
	private SessionFactory sessionFactory;

	public void addVO(BaseVO vo) {

		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		session.save(vo);
		tx.commit();
		session.close();
	}

	public void deleteVOById(BaseVO vo) {
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		session.delete(vo);
		tx.commit();
		session.close();
	}

	public BaseVO getVOById(BaseVO vo) {
		Session session = sessionFactory.openSession();
		BaseVO result = (BaseVO) session.get(
				vo.getClass(), vo.getId());
		
		session.close();
		return result;
	}

	public List<BaseVO> queryAll(String className) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM " + className);
		List<BaseVO> bvs = query.list();

		return bvs;
	}

	public List<BaseVO> queryVO(BaseVO vo) {
		return null;
	}

	public QueryResult<BaseVO> queryVOBySQL(String sql, String className,
			int pageRecords, int offset) {
		Session session = sessionFactory.openSession();

		Long count = (Long) session.createQuery(
				"SELECT count(id) FROM  " + className + " s").uniqueResult();

		Query query = session.createQuery("FROM " + className);
		query.setMaxResults(pageRecords);
		List<BaseVO> bvs = query.setFirstResult(offset).list();

		QueryResult<BaseVO> result = new QueryResult<BaseVO>();
		result.setResultlist(bvs);
		result.setTotalrecord(count);

		return result;
	}

	public void updateVO(BaseVO vo) {
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		session.update(vo);
		tx.commit();
		session.close();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("deprecation")
	public String export() {
		try {
			File file = new File("product_" + System.currentTimeMillis()
					+ ".csv");
			BufferedWriter w = new BufferedWriter(new FileWriter("product_"
					+ System.currentTimeMillis() + ".csv"));
			
			Session session = sessionFactory.openSession();
			Connection conn = session.connection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product");
			ResultSetMetaData meta = rs.getMetaData();
			int count = meta.getColumnCount();
			List<String> cols = new ArrayList<String>();
			String header = "";
			
			for(int i =1;i<=count;i++){
				cols.add(meta.getColumnName(i));
				header+=(cols.get(i-1)+",");
			}
			header = header.substring(0,header.length()-1);
			System.out.println("header="+header);
			//System.out.println("cols: = "+header);
			w.write(header);
			w.newLine();
			
			while(rs.next()){
				String line = "";
				for(String name : cols){
					line+=((rs.getObject(name))+",");
				}
				line = line.substring(0,line.length()-1);
				w.write(line);
				w.newLine();
			}
			
			w.flush();
			w.close();
			session.close();
			return file.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}