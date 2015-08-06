package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有删除阶段任务业务
 */
public class DelTodo {
	/**
	 * 删除待办事项
	 * @param todoId 待办事项id
	 * @return 删除成功返回true，否者返回false
	 * @throws SQLException
	 */
	public boolean delTodo(String todoId) throws SQLException{
		return (new TodoDB()).delTodo(todoId) ;
	}
}
