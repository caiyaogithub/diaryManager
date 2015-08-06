package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;

/**
 * 
 * @author caiyao 
 *
 * @function 执行删除阶段任务的所有功能
 */
public class DelPhaseJob {
	public boolean delPhasejob(String id ) throws SQLException{
		
		return (new PhaseJobDB()).delPhasejobById(id) ;
	}
}
