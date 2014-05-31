package pl.edu.agh.dsm.monitor.web.infrastructure;

import org.springframework.security.core.userdetails.User;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

/**
 * Created by Tom on 2014-05-31.
 */
public class UserConverter {
	public static ApplicationUser convert(User user) {
		if(user == null) return null;
		return new ApplicationUser(user.getUsername());
	}
}
