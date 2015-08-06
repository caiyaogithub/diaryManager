package diaryschedule.dataBean;

import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function ´æ´¢´ý°ìÊÂÏî
 */
public class Todo {
	private String id ;
	private String desc ;
	private Date end_date ;
	private int priority ;
	public Todo() {
	}
	public Todo(String id, String desc, Date end_date, int priority) {
		super();
		this.id = id;
		this.desc = desc;
		this.end_date = end_date;
		this.priority = priority;
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
	@Override
	public String toString() {
		return "Todo [id=" + id + ", desc=" + desc + ", end_date=" + end_date
				+ ", priority=" + priority + "]";
	}
}
