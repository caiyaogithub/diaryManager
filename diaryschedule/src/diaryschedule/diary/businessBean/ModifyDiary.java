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
 * @function 修改日记
 */
public class ModifyDiary {
	public boolean modifyDiary(Diary diary , String htmlPhysicalPath ) 
			throws SQLException, IOException{
			// 该类主要完成两方面问题 1. 将diary中content内容写进htmlUrl所指的文件中。2. 将数据库中日记信息更新
		FileUtils.WriteStringToFile(diary.getContent(), htmlPhysicalPath ) ;
		return (new DiaryDB()).modifyDiary(diary) ;
		
	}
}
