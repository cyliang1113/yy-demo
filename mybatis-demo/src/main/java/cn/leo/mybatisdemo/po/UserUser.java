package cn.leo.mybatisdemo.po;

public class UserUser {

	private Long userId;
	private String username;
	private String cellphone;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Override
	public String toString() {
		return "UserUser [userId=" + userId + ", username=" + username + ", cellphone=" + cellphone + "]";
	}
	
	

}
