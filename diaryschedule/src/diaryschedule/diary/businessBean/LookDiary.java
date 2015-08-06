package diaryschedule.diary.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import diaryschedule.DBoperator.DiaryDB;
import diaryschedule.dataBean.Diary;

/**
 * 
 * @author caiyao 
 *
 * @function �鿴�ռ�
 */
public class LookDiary {
	/**
	 * �����û�id��ѯ�ռ�
	 * @param userId �û�id
	 * @return ArrayList��Diary�� ��ѯ���
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
public ArrayList<Diary> getDiaryList(String userId) 
		throws InstantiationException, IllegalAccessException, 
		NoSuchMethodException, ClassNotFoundException, 
		InvocationTargetException, SQLException, RuntimeException{
		return (new DiaryDB()).getDiaryList(userId) ;
	}
}
