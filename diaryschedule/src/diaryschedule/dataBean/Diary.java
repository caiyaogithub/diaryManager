package diaryschedule.dataBean;

import java.util.Date;

/**
 * 
 * @author caiyao 
 *
 * @function ¥Ê¥¢»’º«
 */
public class Diary {
	private String id ;
	private String title ;
	private String content ;
	private Date date ;
	private String mood ;
	private String weather ;
	public Diary() {
	}
	public Diary(String id, String title, String content, Date date,
			String mood, String weather) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
		this.mood = mood;
		this.weather = weather;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "Diary [id=" + id + ", title=" + title + ", content=" + content
				+ ", date=" + date + ", mood=" + mood + ", weather=" + weather
				+ "]";
	}
}
