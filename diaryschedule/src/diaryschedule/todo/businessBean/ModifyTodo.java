package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;
import diaryschedule.dataBean.Todo;

/**
 * 
 * @author caiyao 
 *
 * @function ִ���޸Ĵ������������ҵ�����
 */
public class ModifyTodo {
	/**
	 * �޸Ĵ�������
	 * @param todo
	 * @return
	 * @throws SQLException
	 */
	public boolean modifyTodo(Todo todo) throws SQLException{
		return (new TodoDB()).modifyTodo(todo) ;
	}
}
