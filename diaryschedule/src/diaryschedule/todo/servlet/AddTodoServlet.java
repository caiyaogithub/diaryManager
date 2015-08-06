package diaryschedule.todo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.Todo;
import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.todo.businessBean.AddTodo;
import diaryschedule.utils.Convert;
import diaryschedule.utils.DateUtils;

/**
 * 
 * @author caiyao 
 *
 * @function ��Ӵ�������
 */
@WebServlet(name="addTodo",urlPatterns="/addTodo")
public class AddTodoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp ) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Todo todo = null;
		try {
			
			todo = new Todo(
					 DateUtils.getFormatDate("yyyyMMddhhmmss"), 
					req.getParameter("desc").trim() ,
					Convert.StringTodate(req.getParameter("end_date").trim() , "yyyy-MM-dd") ,
					Integer.parseInt(req.getParameter("priority").trim())
					);
		} catch (NumberFormatException | ParseException e1) {
			e1.printStackTrace();
			resp.getWriter().write("parameter unvalid!");
			return ;
		}
		
		try {
			if((new AddTodo()).addTodo(todo,((UserLoginInfo)req.getSession().getAttribute("user")).getId())){
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
