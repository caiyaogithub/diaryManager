package diaryschedule.todo.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.UserLoginInfo;
import diaryschedule.todo.businessBean.LookTodo;

/**
 * 
 * @author caiyao 
 *
 * @function 查看待办事项
 */
@WebServlet(name="lookTodo",urlPatterns="/lookTodo")
public class LookTodoServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			doPost(req , resp) ;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		UserLoginInfo user = ((UserLoginInfo)req.getSession().getAttribute("user")) ;
		if(user == null){
			resp.sendRedirect("./login/login.jsp");
			return ;
		}
		try{
			req.setAttribute("todoList", (new LookTodo()).getTodoList(user.getId()));
			//resp.setContentType("text/html; charset=utf-8");
			 //定向的页面
			getServletContext().getRequestDispatcher("/todo/todo.jsp").forward(req, resp);
			return ;
		}catch(SQLException  | InstantiationException 
			   | IllegalAccessException | NoSuchMethodException 
			   | ClassNotFoundException | InvocationTargetException 
			   | RuntimeException e){
		  e.printStackTrace() ;  
		  resp.sendRedirect("./error.jsp");
		  return ;
		}
		
		
		
		
	}
}
