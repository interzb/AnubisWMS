package com.inventory.dao;

 
import java.util.List;  
import com.inventory.model.BaseVO;
import com.inventory.model.QueryResult;
 
public interface BaseDao {  
     public void addVO(BaseVO vo);  
     public void deleteVOById(BaseVO vo);  
     public void updateVO(BaseVO vo);  
     public BaseVO getVOById(BaseVO id);  
     public List<BaseVO> queryVO(BaseVO obj);  
     public QueryResult<BaseVO> queryVOBySQL(String sql,String className,int pageRecords,int offset) ;
     public List<BaseVO> queryAll(String className);  
     public String export();
}   