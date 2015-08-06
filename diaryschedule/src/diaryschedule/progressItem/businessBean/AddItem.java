package diaryschedule.progressItem.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.ProgressItemDB;
import diaryschedule.dataBean.ProgressItem;
import diaryschedule.utils.DateUtils;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有完成进度项的添加功能
 */
public class AddItem {
	public boolean addProgressItem(ProgressItem item , String phasejobId) throws SQLException{
		// 在该Bean中获取完成进度项id，并传进相应的DB方法中
		return (new ProgressItemDB()).addProgressItem(item , phasejobId , DateUtils.getFormatDate("yyyyMMddhhmmss")) ;
	}
}
