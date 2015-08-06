package diaryschedule.progressItem.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.progressItem.businessBean.LookItem;

/**
 * 
 * @author caiyao 
 *
 * @function 查看完成进度项
 */
@WebServlet(name="lookItem",urlPatterns="/lookProgress")
public class LookItemServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String phasejobId = req.getParameter("id").trim() ;
		try {
			System.out.println((new LookItem()).getProgressItemList(phasejobId));
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write((new LookItem()).getProgressItemList(phasejobId));
			return ;
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | ClassNotFoundException
				| InvocationTargetException | SQLException | RuntimeException e) {
			e.printStackTrace();
			resp.sendRedirect("./error.jsp");
			return ;
		}
	}
}
