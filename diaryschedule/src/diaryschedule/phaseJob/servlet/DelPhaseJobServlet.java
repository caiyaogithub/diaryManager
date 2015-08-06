package diaryschedule.phaseJob.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.phaseJob.businessBean.DelPhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function 执行阶段任务的删除
 */
@WebServlet(name="delPhaseJob",urlPatterns="/delPhaseJob")
public class DelPhaseJobServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id").trim() ;
		try{
			if((new DelPhaseJob()).delPhasejob(id)){
				resp.getWriter().write("success") ;
			}else{
				resp.getWriter().write("failed") ;
			}
		}catch(SQLException e){
			e.printStackTrace() ; 
			resp.sendRedirect("./error.jsp");
		}
	}
}
