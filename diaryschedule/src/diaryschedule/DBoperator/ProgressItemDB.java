package diaryschedule.DBoperator;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;

import diaryschedule.DBManager.DBManager;
import diaryschedule.dataBean.ProgressItem;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有完成进度项的数据库操作
 */
public class ProgressItemDB {
	/**
	 * 根据阶段任务id查询该阶段任务下的所有完成进度项
	 * @param phasejobId 阶段任务id
	 * @return ArrayList《ProgressItem》查询结果
	 * @throws RuntimeException 
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ArrayList<ProgressItem> getProgressItemList(String phasejobId) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, RuntimeException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectItemByPhaseId(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, phasejobId) ;
		CachedRowSetImpl cachedRS = new CachedRowSetImpl() ;
		cachedRS.populate(call.executeQuery()) ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		return Convert.RStoList(cachedRS, ProgressItem.class) ;
	}
	public boolean addProgressItem(ProgressItem item , String phasejobId , String progressItemId ) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call addProgressItem(?,?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, progressItemId) ;
		call.setString(2, phasejobId );
		call.setString(3, item.getDesc()) ;
		call.setDate(4,   new java.sql.Date(item.getStart_date().getTime())) ;
		call.setDate(5,  new java.sql.Date(item.getEnd_date().getTime())) ;
		call.setString(6, item.getTarget()) ;
		int effectRowNum = call.executeUpdate() ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		if(effectRowNum == 1){
			return true ;
		}else{
			return false ;
		}	
	}
}
