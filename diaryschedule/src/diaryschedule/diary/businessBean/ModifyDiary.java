package diaryschedule.diary.businessBean;

import java.io.IOException;
import java.sql.SQLException;

import diaryschedule.DBoperator.DiaryDB;
import diaryschedule.dataBean.Diary;
import diaryschedule.utils.FileUtils;

/**
 * 
 * @author caiyao 
 *
 * @function �޸��ռ�
 */
public class ModifyDiary {
	public boolean modifyDiary(Diary diary , String htmlPhysicalPath ) 
			throws SQLException, IOException{
			// ������Ҫ������������� 1. ��diary��content����д��htmlUrl��ָ���ļ��С�2. �����ݿ����ռ���Ϣ����
		FileUtils.WriteStringToFile(diary.getContent(), htmlPhysicalPath ) ;
		return (new DiaryDB()).modifyDiary(diary) ;
		
	}
}
