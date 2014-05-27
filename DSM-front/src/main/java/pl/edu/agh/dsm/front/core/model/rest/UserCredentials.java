package pl.edu.agh.dsm.front.core.model.rest;

/**
 * Created by Tom on 2014-05-26.
 */
public class UserCredentials {

	String username;
	String password;

	public UserCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
