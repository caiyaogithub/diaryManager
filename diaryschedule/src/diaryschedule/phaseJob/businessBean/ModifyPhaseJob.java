package diaryschedule.phaseJob.businessBean;

import java.sql.SQLException;

import diaryschedule.DBoperator.PhaseJobDB;
import diaryschedule.dataBean.PhaseJob;

/**
 * 
 * @author caiyao 
 *
 * @function ִ�н׶������޸�
 */
public class ModifyPhaseJob {
	/**
	 * �޸Ľ׶�����
	 * @param phasejob �׶��������PhaseJob
	 * @return �޸ĳɹ�����true ���߷���false
	 * @throws SQLException 
	 */
	public boolean modifyPhasejob(PhaseJob phasejob) throws SQLException{
		
		return (new PhaseJobDB()).modifyphasejob(phasejob) ;
	}
}
