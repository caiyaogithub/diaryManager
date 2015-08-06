package diaryschedule.login.serlvlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.login.businessBean.Login;

/**
 * 
 * @author caiyao 
 *
 * @function µÇÂ½´¦Àí
 */
@WebServlet(name="login",urlPatterns="/login")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userId = req.getParameter("id") ;
		String password = req.getParameter("password") ;
		Login login = new Login() ;
		try{
			if(login.hasUser(userId , password)){
				req.getSession().setAttribute("user", login.queryUserInfo(userId));
				resp.sendRedirect("./main.jsp");
				return ;
			}else{
				resp.sendRedirect("./login/login.jsp");
				return ;
			}
		}catch(SQLException  | InstantiationException 
			   | IllegalAccessException | NoSuchMethodException 
			   | ClassNotFoundException | InvocationTargetException 
			   | RuntimeException e){
		  e.printStackTrace() ;  
		  resp.sendRedirect("./error.jsp");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.sendRedirect("./login/login.jsp") ;
			return ;
	}
}
