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
 * @function 执行查看阶段任务所有功能
 */
public class LookPhaseJob {
	/**
	 * 根据用户id查询其所有的阶段任务
	 * @param userId　用户id
	 * @return ArrayList《PhaseJob》
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
