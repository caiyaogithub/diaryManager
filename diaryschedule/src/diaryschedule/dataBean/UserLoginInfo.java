package diaryschedule.dataBean;
/**
 * 
 * @author caiyao 
 *
 * @function 存放用户登陆信息，包括用户名、用户ID
 */
public class UserLoginInfo {
	private String nickname ;
	private String id ;
	
	public UserLoginInfo() {
	}
	public UserLoginInfo(String nickname, String id) {
		super();
		this.nickname = nickname;
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "UserLoginInfo [userName=" + nickname + ", id=" + id
				+ "]";
	}
}
