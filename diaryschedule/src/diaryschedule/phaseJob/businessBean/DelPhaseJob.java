package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;

/**
 * 
 * @author caiyao 
 *
 * @function ִ��ɾ���׶���������й���
 */
public class DelPhaseJob {
	public boolean delPhasejob(String id ) throws SQLException{
		
		return (new PhaseJobDB()).delPhasejobById(id) ;
	}
}
