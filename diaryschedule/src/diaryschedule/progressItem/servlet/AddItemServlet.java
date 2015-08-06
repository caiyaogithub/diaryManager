package diaryschedule.progressItem.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.ProgressItem;
import diaryschedule.progressItem.businessBean.AddItem;
import diaryschedule.utils.Convert;

/**
 * 
 * @author caiyao 
 *
 * @function 添加完成进度项
 */
@WebServlet(name="addItem",urlPatterns="/addProgressItem")
public class AddItemServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProgressItem item = null ;
		try {
			        item = new ProgressItem(
					req.getParameter("desc"),
					Convert.StringTodate(req.getParameter("start_date"), "yyyy-MM-dd") ,
					Convert.StringTodate(req.getParameter("end_date"), "yyyy-MM-dd") ,
					req.getParameter("target")
					) ;
		} catch (ParseException e) {
			e.printStackTrace();
			resp.getWriter().write("parameter unvalid !") ;
		}
		try {
			if((new AddItem()).addProgressItem(item , req.getParameter("phasejobId"))){
				resp.getWriter().write("success") ;
				return ;
			}else{
				resp.getWriter().write("failed") ;
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("./error.jsp") ;
			return ;
		}
	}
}
