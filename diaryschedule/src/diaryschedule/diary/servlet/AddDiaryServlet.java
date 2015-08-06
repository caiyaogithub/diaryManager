package diaryschedule.diary.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.Diary;
import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.diary.businessBean.AddDiary;
import diaryschedule.utils.DateUtils;
import diaryschedule.utils.FileUtils;

/**
 * 
 * @author caiyao 
 *
 * @function ÃÌº”»’º«
 */
@WebServlet(name="addDiary" , urlPatterns="/addDiary")
public class AddDiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UserLoginInfo user = ((UserLoginInfo)req.getSession().getAttribute("user")) ;
		if(user == null){
			resp.sendRedirect("./login/login.jsp");
			return ;
		}
		String diaryHtmlFileName = DateUtils.getFormatDate("yyyyMMddhhmmss") + ".html" ;
		String targetFilePath = 
				getServletContext().getInitParameter("webRoot") + "diarys/" + user.getId() + "/" + diaryHtmlFileName ;
		FileUtils.WriteStringToFile(req.getParameter("contentHtml") , targetFilePath) ;
		Diary diary = new Diary(
				DateUtils.getFormatDate("yyyyMMddhhmmss") ,
				req.getParameter("title") , 
				getServletContext().getInitParameter("webPrefix") + "diarys/" + user.getId() + "/" + diaryHtmlFileName ,
				new Date() , 
				req.getParameter("mood") , 
				req.getParameter("weather")) ;
		try {
			if((new AddDiary()).addDiary(diary , user.getId())){
				resp.getWriter().write("success");
				return ;
			}else{
				resp.getWriter().write("failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("./error.jsp");
		}
	}
}
