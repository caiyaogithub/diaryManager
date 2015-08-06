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
 * @function 获取日记内容
 */
@WebServlet(name="getDiaryContent",urlPatterns="/getDiaryContent")
public class GetDiaryContent extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取HTML url对应的HTML文件在文件系统中的存放路径
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
