package diaryschedule.todo.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.TodoDB;

/**
 * 
 * @author caiyao 
 *
 * @function ִ������ɾ���׶�����ҵ��
 */
public class DelTodo {
	/**
	 * ɾ����������
	 * @param todoId ��������id
	 * @return ɾ���ɹ�����true�����߷���false
	 * @throws SQLException
	 */
	public boolean delTodo(String todoId) throws SQLException{
		return (new TodoDB()).delTodo(todoId) ;
	}
}
