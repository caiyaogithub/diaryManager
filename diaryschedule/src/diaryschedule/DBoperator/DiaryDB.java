package diaryschedule.DBoperator;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;

import diaryschedule.DBManager.DBManager;
import diaryschedule.dataBean.Diary;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有关于日记的数据库操作
 */
public class DiaryDB {
	/**
	 * 根据用户id查询数据库
	 * @param userId 用户id
	 * @return ArrayList《Diary》查询结果
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	public ArrayList<Diary> getDiaryList(String userId) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, RuntimeException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectDiaryByUserId(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, userId) ;
		CachedRowSetImpl cachedRS = new CachedRowSetImpl() ;
		cachedRS.populate(call.executeQuery()) ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		return Convert.RStoList(cachedRS, Diary.class) ;
	}
	public boolean addDiary(Diary diary , String userId) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call addDiary(?,?,?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, diary.getId()) ;
		call.setString(2, userId );
		call.setString(3, diary.getTitle()) ;
		call.setString(4,   diary.getContent()) ;
		call.setDate(5,  Convert.utildateTosqldate(diary.getDate())) ;
		call.setString(6, diary.getMood()) ;
		call.setString(7, diary.getWeather()) ;
		int effectRowNum = call.executeUpdate() ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		if(effectRowNum == 1){
			return true ;
		}else{
			return false ;
		}
	}
	/**
	 * 根据日记ID删除数据库中日记记录
	 * @param diaryId 日记ID
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean delDiary(String diaryId) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call delDiaryById(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, diaryId) ;
		int effectRowNum = call.executeUpdate() ;
		if(effectRowNum == 1){
			return true ;
		}else{
		return false ;
		}
	}
	public boolean modifyDiary(Diary diary ) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call modifyDiary(?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, diary.getId()) ;
		call.setString(2, diary.getTitle()) ;
		call.setDate(3,   Convert.utildateTosqldate(diary.getDate())) ;
		call.setString(4,  diary.getMood()) ;
		call.setString(5, diary.getWeather()) ;
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
