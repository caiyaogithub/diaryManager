package diaryschedule.login.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import diaryschedule.DBoperator.LoginDB;
import diaryschedule.dataBean.UserLoginInfo;

/**
 * 
 * @author caiyao 
 *
 * @function ִ�е�½��ҵ�����
 */
public class Login {
	public boolean hasUser(String userId , String password) throws SQLException{
		boolean exist ; 
		exist = (new LoginDB()).hasUser(userId , password ) ;
		return exist ;
	}
	/**
	 * �����û�id��ѯ�û���Ϣ
	 * @param userId �û�id
	 * @return UserLoginInfo����
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
