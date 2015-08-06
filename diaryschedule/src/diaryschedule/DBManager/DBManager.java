package diaryschedule.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * 
 * @author caiyao 
 *
 * @function ��ȡ����
 */
public class DBManager {
	private static Stack<Connection> conns = new Stack<Connection>(); //���ݿ����ӳ�
	static{
		// ��ʼ�����ӳ�
		String url = "jdbc:mysql://localhost:3306/diaryschedule" ;
		String driver = "com.mysql.jdbc.Driver" ;
		String user = "root" ;
		String password = "CY3datebase" ;
		int connNum = 20 ; // ���ӳ�������
		try {
			Class.forName(driver) ;
			for(int i = 0 ; i < connNum ; i ++ ){
				Connection conn = DriverManager.getConnection(url, user, password) ;
				conns.push(conn) ;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		if(conns.isEmpty()){
			System.out.println("��ʱû�п�������!��ȴ�2����~");
			try {
				Thread.sleep(2000);
				return getConnection() ;
			} catch (InterruptedException e) {
				e.printStackTrace() ;
				return null ;
			}
		}else{
			return conns.pop() ;
		}
	}
	public void returnConn(Connection conn){
		conns.push(conn) ;
	}
}
