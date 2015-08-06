package diaryschedule.diary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.utils.FileUtils;

/**
 * 
 * @author caiyao 
 *
 * @function ��ȡ�ռ�����
 */
@WebServlet(name="getDiaryContent",urlPatterns="/getDiaryContent")
public class GetDiaryContent extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// ��ȡHTML url��Ӧ��HTML�ļ����ļ�ϵͳ�еĴ��·��
		String htmlFilePhysicalPath = getServletContext().getInitParameter("webRoot") 
				+ req.getParameter("htmlUrl").substring(36) ;
		System.out.println(FileUtils.getFileContent(htmlFilePhysicalPath));
		resp.setCharacterEncoding("utf-8") ;
		resp.getWriter().write(FileUtils.getFileContent(htmlFilePhysicalPath)) ;
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp ) ;
	}
}
