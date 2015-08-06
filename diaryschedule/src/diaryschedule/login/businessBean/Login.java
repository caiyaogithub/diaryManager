package diaryschedule.login.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import diaryschedule.DBoperator.LoginDB;
import diaryschedule.dataBean.UserLoginInfo;

/**
 * 
 * @author caiyao 
 *
 * @function 执行登陆的业务操作
 */
public class Login {
	public boolean hasUser(String userId , String password) throws SQLException{
		boolean exist ; 
		exist = (new LoginDB()).hasUser(userId , password ) ;
		return exist ;
	}
	/**
	 * 根据用户id查询用户信息
	 * @param userId 用户id
	 * @return UserLoginInfo对象
	 * @throws RuntimeException 
	 * @throws SQLException 
	 * @throws InvocationTargetException 
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public UserLoginInfo queryUserInfo(String userId)
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, 
			RuntimeException{
		
		return (new LoginDB()).selectUserInfoById(userId) ;
	}
}
