package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;
import diaryschedule.dataBean.Todo;

/**
 * 
 * @author caiyao 
 *
 * @function 添加待办事项
 */
public class AddTodo {
	/**
	 * 添加待办事项
	 * @param todo 待办事项对象
	 * @param user_id 用户id
	 * @return 如果添加成功返回true，否者返回false
	 * @throws SQLException
	 */
	public boolean addTodo(Todo todo , String user_id) throws SQLException{
		return (new TodoDB()).addTodo(todo , user_id) ;
	}
}
