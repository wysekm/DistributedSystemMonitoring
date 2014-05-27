package pl.edu.agh.dsm.front.web.Infrastructure;

import org.springframework.security.core.userdetails.User;
import pl.edu.agh.dsm.front.core.model.rest.UserCredentials;

/**
 * Created by Tom on 2014-05-26.
 */
public class UserConverter {

	public static UserCredentials convert(User user) {
		if(user == null) {
			return null;
		}
		return new UserCredentials(user.getUsername(), user.getPassword());
	}
}
