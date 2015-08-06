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
 * @function 查看日记
 */
public class LookDiary {
	/**
	 * 根据用户id查询日记
	 * @param userId 用户id
	 * @return ArrayList《Diary》 查询结果
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
