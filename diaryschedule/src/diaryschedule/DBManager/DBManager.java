package diaryschedule.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

/**
 * 
 * @author caiyao 
 *
 * @function 获取连接
 */
public class DBManager {
	private static Stack<Connection> conns = new Stack<Connection>(); //数据库连接池
	static{
		// 初始化连接池
		String url = "jdbc:mysql://localhost:3306/diaryschedule" ;
		String driver = "com.mysql.jdbc.Driver" ;
		String user = "root" ;
		String password = "CY3datebase" ;
		int connNum = 20 ; // 连接池连接数
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
			System.out.println("暂时没有空闲连接!请等待2秒钟~");
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
