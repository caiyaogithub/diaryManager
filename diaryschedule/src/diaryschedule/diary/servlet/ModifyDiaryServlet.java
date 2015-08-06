package diaryschedule.diary.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.Diary;
import diaryschedule.diary.businessBean.ModifyDiary;

/**
 * 
 * @author caiyao 
 *
 * @function ÐÞ¸ÄÈÕ¼Ç
 * 
 */
@WebServlet(name="modifyDiary",urlPatterns="/modifyDiary")
public class ModifyDiaryServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Diary diary = null ;
		try {
			diary = new Diary(
					req.getParameter("id") ,
					req.getParameter("title") ,
					req.getParameter("contentHtml") ,
					new Date() ,
					req.getParameter("mood") ,
					req.getParameter("weather") 
					) ;
		String htmlUrl = req.getParameter("htmlUrl") ;
			if((new ModifyDiary()).modifyDiary(diary , getServletContext().getInitParameter("webRoot") + htmlUrl.substring(36))){
				resp.getWriter().write("success");
				return ;
			}else{
				resp.getWriter().write("failed") ;
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("./error.jsp");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp ) ;
	}
}
