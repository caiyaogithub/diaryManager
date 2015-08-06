package diaryschedule.phaseJob.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.PhaseJob;
import diaryschedule.phaseJob.businessBean.ModifyPhaseJob;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function ÐÞ¸Ä½×¶ÎÈÎÎñ
 */
@SuppressWarnings("deprecation")
@WebServlet(name="modifyPhaseJob",urlPatterns="/modifyPhaseJob")
public class ModifyPhaseJobServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PhaseJob phasejob = null;
		System.out.println(req.getParameter("desc").trim());
		try {
			phasejob = new PhaseJob(
					req.getParameter("id").trim() , 
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
			if((new ModifyPhaseJob()).modifyPhasejob(phasejob)){
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
