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
 * @function ִ�����в鿴��������Ĺ���
 */
public class LookTodo {
	/**
	 * ͨ���û�id��ȡ���û����������б�
	 * @param user_id �û�id
	 * @return  ArrayList��Todo����ѯ���
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
