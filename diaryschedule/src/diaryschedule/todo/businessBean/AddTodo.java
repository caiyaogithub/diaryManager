package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;
import diaryschedule.dataBean.Todo;

/**
 * 
 * @author caiyao 
 *
 * @function ��Ӵ�������
 */
public class AddTodo {
	/**
	 * ��Ӵ�������
	 * @param todo �����������
	 * @param user_id �û�id
	 * @return �����ӳɹ�����true�����߷���false
	 * @throws SQLException
	 */
	public boolean addTodo(Todo todo , String user_id) throws SQLException{
		return (new TodoDB()).addTodo(todo , user_id) ;
	}
}
