package diaryschedule.DBoperator;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.sun.rowset.CachedRowSetImpl;

import diaryschedule.DBManager.DBManager;
import diaryschedule.dataBean.PhaseJob;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function ִ�н׶��������е����ݿ����
 */
public class PhaseJobDB {
	/**
	 * ��ѯuserId��Ӧ�����н׶�������Ϣ������Ϣ������ִ�н�����
	 * @param userId �� �û�id
	 * @return ArrayList�� PhaseJob��
	 * @throws SQLException 
	 * @throws RuntimeException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ArrayList<PhaseJob> getPhaseByuserId(String userId)
			throws SQLException, InstantiationException, 
			IllegalAccessException, NoSuchMethodException, 
			ClassNotFoundException, InvocationTargetException, 
			RuntimeException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectPhaseJobsByUserId(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, userId) ;
		CachedRowSetImpl cachedRS = new CachedRowSetImpl() ;
		cachedRS.populate(call.executeQuery()) ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		return Convert.RStoList(cachedRS, PhaseJob.class) ;
	}
	/**
	 * ���ݽ׶������ɾ���׶�����
	 * @param id �׶������
	 * @return ɾ���ɹ�����true���߷���false
	 * @throws SQLException
	 */
	public boolean delPhasejobById(String id ) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call delPhasejobById(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, id) ;
		int effectRowNum = call.executeUpdate() ;
		if(effectRowNum == 1){
			return true ;
		}else{
		return false ;
		}
	}
	public boolean modifyphasejob(PhaseJob phasejob) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call modifyPhaseJob(?,?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, phasejob.getId()) ;
		call.setString(2, phasejob.getDesc()) ;
		call.setDate(3,   new java.sql.Date(phasejob.getStart_date().getTime())) ;
		call.setDate(4,  new java.sql.Date(phasejob.getEnd_date().getTime())) ;
		call.setInt(5, phasejob.getPriority()) ;
		call.setString(6, phasejob.getTarget()) ;
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
	 * ִ����ӽ׶���������ݿ����
	 * @param phasejob �׶��������
	 * @param user_id �û�id
	 * @return
	 * @throws SQLException
	 */
	public boolean addPhasejob(PhaseJob phasejob , String user_id ) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call addPhaseJob(?,?,?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, phasejob.getId()) ;
		call.setString(2, user_id );
		call.setString(3, phasejob.getDesc()) ;
		call.setDate(4,   new java.sql.Date(phasejob.getStart_date().getTime())) ;
		call.setDate(5,  new java.sql.Date(phasejob.getEnd_date().getTime())) ;
		call.setInt(6, phasejob.getPriority()) ;
		call.setString(7, phasejob.getTarget()) ;
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
