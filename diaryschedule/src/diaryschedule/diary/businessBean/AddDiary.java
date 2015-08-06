package diaryschedule.diary.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.DiaryDB;
import diaryschedule.dataBean.Diary;

/**
 * 
 * @author caiyao 
 *
 * @function ÃÌº”»’º«
 */
public class AddDiary {
	public boolean addDiary(Diary diary , String userId ) throws SQLException{
		return (new DiaryDB()).addDiary(diary , userId) ;
	}
}
