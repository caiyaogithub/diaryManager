package diaryschedule.phaseJob.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.phaseJob.businessBean.LookPhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function 查看阶段任务
 */
@WebServlet(name="lookPhaseJob",urlPatterns="/lookPhaseJob")
public class LookPhaseJobServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp) ;
		return ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UserLoginInfo user = ((UserLoginInfo)req.getSession().getAttribute("user")) ;
		if(user == null){
			resp.sendRedirect("./login/login.jsp");
			return ;
		}
		try{
			req.setAttribute("phaseJobs", (new LookPhaseJob()).getPhaseList(user.getId()));
			//resp.setContentType("text/html; charset=utf-8");
			 //定向的页面
			getServletContext().getRequestDispatcher("/phaseJob/phaseJob.jsp").forward(req, resp);
			return ;
		}catch(SQLException  | InstantiationException 
			   | IllegalAccessException | NoSuchMethodException 
			   | ClassNotFoundException | InvocationTargetException 
			   | RuntimeException e){
		  e.printStackTrace() ;  
		  resp.sendRedirect("./error.jsp");
		  return ;
		}
	}
}
