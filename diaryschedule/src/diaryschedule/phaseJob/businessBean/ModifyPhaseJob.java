package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;
import diaryschedule.dataBean.PhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function 执行阶段任务修改
 */
public class ModifyPhaseJob {
	/**
	 * 修改阶段任务
	 * @param phasejob 阶段任务对象PhaseJob
	 * @return 修改成功返回true 否者返回false
	 * @throws SQLException 
	 */
	public boolean modifyPhasejob(PhaseJob phasejob) throws SQLException{
		
		return (new PhaseJobDB()).modifyphasejob(phasejob) ;
	}
}
