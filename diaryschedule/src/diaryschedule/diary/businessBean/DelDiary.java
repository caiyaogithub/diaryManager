package diaryschedule.diary.businessBean;

import java.io.File;
import java.sql.SQLException;

import diaryschedule.DBoperator.DiaryDB;

/**
 * 
 * @author caiyao 
 *
 * @function 删除日记
 */
public class DelDiary {
	/**
	 * 执行删除日记业务
	 * @param id 日记ID
	 * @param htmlFilePath 该日记HTML文件存放绝度路径
	 * @return 删除成功返回true 否则返回false
	 * @throws SQLException
	 */
	public boolean delDiary(String id , String htmlFilePath) throws SQLException{
		// 该类主要完成两方面的删除，一是日记HTML文件，而是数据库中该日记记录
		File htmlFile = new File(htmlFilePath) ;
		if(htmlFile.exists()){
			htmlFile.delete() ;
		}
		return (new DiaryDB()).delDiary(id) ;
	}
}
