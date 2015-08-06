package diaryschedule.dataBean;

import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function 存储完成进度项
 */
public class ProgressItem {
	private String desc ;
	private Date start_date ;
	private Date end_date ;
	private String target ;
	public ProgressItem() {
	}
	public ProgressItem(String desc, Date start_date, Date end_date,
			String target) {
		super();
		this.desc = desc;
		this.start_date = start_date;
		this.end_date = end_date;
		this.target = target;
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
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	@Override
	public String toString() {
		return "ProgressItem [desc=" + desc + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", target=" + target + "]";
	}
}
