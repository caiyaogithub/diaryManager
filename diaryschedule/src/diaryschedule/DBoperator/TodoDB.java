package diaryschedule.DBoperator;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.rowset.CachedRowSetImpl;

import diaryschedule.DBManager.DBManager;
import diaryschedule.dataBean.Todo;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function ִ�����д�����������ݿ����
 */
public class TodoDB {
	/**
	 * ��ȡĳ���û��µ����д�������
	 * @return
	 * @throws RuntimeException 
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public ArrayList<Todo> getTodoList(String userId ) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, RuntimeException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectTodosByUserId(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, userId) ;
		CachedRowSetImpl cachedRS = new CachedRowSetImpl() ;
		cachedRS.populate(call.executeQuery()) ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		return Convert.RStoList(cachedRS, Todo.class) ;
	}
	/**
	 * �޸Ĵ�������
	 * @param todo ��������Todo����
	 * @return �޸ĳɹ�����true ���߷���false
	 * @throws SQLException
	 */
	public boolean modifyTodo(Todo todo) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call modifyTodo(?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, todo.getId()) ;
		call.setString(2, todo.getDesc()) ;
		call.setDate(3,  new java.sql.Date(todo.getEnd_date().getTime())) ;
		call.setInt(4, todo.getPriority()) ;
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
	 * ɾ�������������ݿ����
	 * @param id ��������id
	 * @return ɾ���ɹ�����true���߷���false
	 * @throws SQLException
	 */
	public boolean delTodo(String id) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call delTodoById(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, id) ;
		int effectRowNum = call.executeUpdate() ;
		if(effectRowNum == 1){
			return true ;
		}else{
		return false ;
		}
	}
	/**
	 * ��Ӵ�������
	 * @param todo Todo�����������
	 * @param user_id �û�id
	 * @return �����ӳɹ�����true���߷���false
	 * @throws SQLException
	 */
	public boolean addTodo(Todo todo , String user_id) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call addTodo(?,?,?,?,?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, todo.getId()) ;
		call.setString(2, user_id );
		call.setString(3, todo.getDesc()) ;
		call.setDate(4,  new java.sql.Date(todo.getEnd_date().getTime())) ;
		call.setInt(5, todo.getPriority()) ;
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
