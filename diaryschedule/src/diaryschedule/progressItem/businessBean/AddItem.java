package diaryschedule.progressItem.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.ProgressItemDB;
import diaryschedule.dataBean.ProgressItem;
import diaryschedule.utils.DateUtils;

/**
 * 
 * @author caiyao 
 *
 * @function ִ��������ɽ��������ӹ���
 */
public class AddItem {
	public boolean addProgressItem(ProgressItem item , String phasejobId) throws SQLException{
		// �ڸ�Bean�л�ȡ��ɽ�����id����������Ӧ��DB������
		return (new ProgressItemDB()).addProgressItem(item , phasejobId , DateUtils.getFormatDate("yyyyMMddhhmmss")) ;
	}
}
