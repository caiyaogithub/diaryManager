package diaryschedule.todo.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.todo.businessBean.DelTodo;

/**
 * 
 * @author caiyao 
 *
 * @function É¾³ý´ý°ìÊÂÏî
 */
@WebServlet(name="delTodo",urlPatterns="/delTodo")
public class DelTodoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req , resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id").trim() ;
		try{
			if((new DelTodo()).delTodo(id)){
				resp.getWriter().write("success") ;
			}else{
				resp.getWriter().write("failed") ;
			}
		}catch(SQLException e){
			e.printStackTrace() ; 
			resp.sendRedirect("./error.jsp");
		}
	}
}
