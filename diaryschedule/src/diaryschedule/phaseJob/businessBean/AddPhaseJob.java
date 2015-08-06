package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;
import diaryschedule.dataBean.PhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function 执行所有添加阶段任务
 */
public class AddPhaseJob {
	public boolean addPhasejob(PhaseJob phasejob , String user_id ) throws SQLException{
		return (new PhaseJobDB()).addPhasejob(phasejob , user_id ) ;
	}
}
