package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;
import diaryschedule.dataBean.PhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function ִ��������ӽ׶�����
 */
public class AddPhaseJob {
	public boolean addPhasejob(PhaseJob phasejob , String user_id ) throws SQLException{
		return (new PhaseJobDB()).addPhasejob(phasejob , user_id ) ;
	}
}
