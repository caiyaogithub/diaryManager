package diaryschedule.phaseJob.businessBean;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import diaryschedule.DBoperator.PhaseJobDB;
import diaryschedule.dataBean.PhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function ִ�в鿴�׶��������й���
 */
public class LookPhaseJob {
	/**
	 * �����û�id��ѯ�����еĽ׶�����
	 * @param userId���û�id
	 * @return ArrayList��PhaseJob��
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws SQLException
	 * @throws RuntimeException
	 */
	public ArrayList<PhaseJob> getPhaseList(String userId) 
			throws InstantiationException, IllegalAccessException, 
			NoSuchMethodException, ClassNotFoundException, 
			InvocationTargetException, SQLException, 
			RuntimeException{
		
		return (new PhaseJobDB()).getPhaseByuserId(userId) ;
	}
}
