package diaryschedule.DBoperator;

import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

import diaryschedule.DBManager.DBManager;
import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function 执行登陆业务的所有数据库操作
 */
public class LoginDB {
	/**
	 * 判断是否存在参数指定的用户
	 * @param userId 用户id
	 * @param password 密码
	 * @return 存在返回 true 否者false
	 * @throws SQLException
	 */
	public boolean hasUser(String userId , String password) throws SQLException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectUserByIdandPassword(? , ?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, userId ) ;
		call.setString(2, password) ;
		boolean exist = call.executeQuery().next() ;
		call.close(); 
		dbManager.returnConn(conn) ;
		return  exist;
	}
	public UserLoginInfo selectUserInfoById(String userId) 
			throws SQLException, InstantiationException, 
			IllegalAccessException, NoSuchMethodException, 
			ClassNotFoundException, InvocationTargetException, 
			RuntimeException{
		DBManager dbManager = new DBManager() ;
		Connection conn = dbManager.getConnection() ;
		String sql = "call selectUserById(?)" ;
		CallableStatement call = conn.prepareCall(sql) ;
		call.setString(1, userId) ;
		CachedRowSetImpl cachedRS = new CachedRowSetImpl() ;
		cachedRS.populate(call.executeQuery()) ;
		cachedRS.first() ;
		call.close() ; 
		dbManager.returnConn(conn) ;
		return Convert.RStoObject(cachedRS, UserLoginInfo.class) ;
	}
}
