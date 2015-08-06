package diaryschedule.dataBean;

import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function 存储阶段任务信息，不包括完成进度项
 */
public class PhaseJob {
	String id ;
	String desc ;
	Date start_date ;
	Date end_date ;
	int priority ;
	String target ;
	public PhaseJob() {
		super();
	}
	public PhaseJob(String id, String desc, Date start_date, Date end_date,
			int priority, String target) {
		super();
		this.id = id;
		this.desc = desc;
		this.start_date = start_date;
		this.end_date = end_date;
		this.priority = priority;
		this.target = target;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date ;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "PhaseJob [id=" + id + ", desc=" + desc + ", start_date="
				+ start_date + ", end_date=" + end_date + ", priority="
				+ priority + ", target=" + target + "]";
	}
}
