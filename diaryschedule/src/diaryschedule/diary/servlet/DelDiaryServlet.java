package diaryschedule.diary.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.diary.businessBean.DelDiary;

/**
 * 
 * @author caiyao 
 *
 * @function ɾ���ռ�
 */
@WebServlet(name="delDiary",urlPatterns="/delDiary")
public class DelDiaryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			// ��ȡHTML url��Ӧ��HTML�ļ����ļ�ϵͳ�еĴ��·��
			String htmlFilePhysicalPath = getServletContext().getInitParameter("webRoot") 
					+ req.getParameter("htmlUrl").substring(36) ;
			if(
					(new DelDiary()).delDiary(req.getParameter("id").trim(),htmlFilePhysicalPath)
			  ){
				resp.getWriter().write("success") ;
			}else{
				resp.getWriter().write("failed") ;
			}
		}catch(SQLException e){
			e.printStackTrace() ; 
			resp.sendRedirect("./error.jsp");
		}
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp) ;
	}
}
