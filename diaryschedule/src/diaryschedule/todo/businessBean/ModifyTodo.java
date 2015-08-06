package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;
import diaryschedule.dataBean.Todo;

/**
 * 
 * @author caiyao 
 *
 * @function 执行修改待办事项的所有业务操作
 */
public class ModifyTodo {
	/**
	 * 修改待办事项
	 * @param todo
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyTodo(Todo todo) throws SQLException{
		return (new TodoDB()).modifyTodo(todo) ;
	}
}
