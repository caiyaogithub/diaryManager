package diaryschedule.todo.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import diaryschedule.DBoperator.TodoDB;
import diaryschedule.dataBean.Todo;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有查看待办事项的功能
 */
public class LookTodo {
	/**
	 * 通过用户id获取该用户待办事项列表
	 * @param user_id 用户id
	 * @return  ArrayList《Todo》查询结果
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	public ArrayList<Todo> getTodoList(String user_id) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, RuntimeException{
		
		return (new TodoDB()).getTodoList(user_id) ;
	}
}
