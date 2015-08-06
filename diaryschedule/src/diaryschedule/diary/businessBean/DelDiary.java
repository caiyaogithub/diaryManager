package diaryschedule.diary.businessBean;

import java.io.File;
import java.sql.SQLException;

import diaryschedule.DBoperator.DiaryDB;

/**
 * 
 * @author caiyao 
 *
 * @function ɾ���ռ�
 */
public class DelDiary {
	/**
	 * ִ��ɾ���ռ�ҵ��
	 * @param id �ռ�ID
	 * @param htmlFilePath ���ռ�HTML�ļ���ž���·��
	 * @return ɾ���ɹ�����true ���򷵻�false
	 * @throws SQLException
	 */
	public boolean delDiary(String id , String htmlFilePath) throws SQLException{
		// ������Ҫ����������ɾ����һ���ռ�HTML�ļ����������ݿ��и��ռǼ�¼
		File htmlFile = new File(htmlFilePath) ;
		if(htmlFile.exists()){
			htmlFile.delete() ;
		}
		return (new DiaryDB()).delDiary(id) ;
	}
}
