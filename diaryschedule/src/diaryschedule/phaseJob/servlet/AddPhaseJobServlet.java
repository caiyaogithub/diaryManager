package diaryschedule.phaseJob.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.PhaseJob;
import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.phaseJob.businessBean.AddPhaseJob;
import diaryschedule.utils.Convert;
import diaryschedule.utils.DateUtils;

/**
 * 
 * @author caiyao 
 *
 * @function Ìí¼Ó½×¶ÎÈÎÎñ
 */
@WebServlet(name="addPhaseJob",urlPatterns="/addPhaseJob")
public class AddPhaseJobServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PhaseJob phasejob = null;
		try {
			
			phasejob = new PhaseJob(
					 DateUtils.getFormatDate("yyyyMMddhhmmss"), 
					req.getParameter("desc").trim() ,
					Convert.StringTodate(req.getParameter("start_date").trim() , "yyyy-MM-dd") ,
					Convert.StringTodate(req.getParameter("end_date").trim() , "yyyy-MM-dd") ,
					Integer.parseInt(req.getParameter("priority").trim()) ,
					req.getParameter("target").trim()
					);
		} catch (NumberFormatException | ParseException e1) {
			e1.printStackTrace();
			resp.getWriter().write("parameter unvalid!");
			return ;
		}
		
		try {
			if((new AddPhaseJob()).addPhasejob(phasejob,((UserLoginInfo)req.getSession().getAttribute("user")).getId())){
				resp.getWriter().write("success");
				return ;
			}else{
				resp.getWriter().write("failed");
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("./error.jsp");
			return ;
		}
	}
}
