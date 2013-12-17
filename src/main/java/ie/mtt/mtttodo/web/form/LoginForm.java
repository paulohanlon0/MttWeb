package ie.mtt.mtttodo.web.form;

public class LoginForm {
	
	private String username;
	private String password;
	private String general;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGeneral() {
		return this.general;
	}
	
	public void setGeneral(String general) {
		this.general = general;
	}
}
