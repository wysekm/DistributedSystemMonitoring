package pl.edu.agh.dsm.catalog.core.model.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.catalog.core.infrastructure.ActionPossibility;

@Component
public class UserAuthorization {
	
	@Value("${monitor.username}")
	String monitorUsername;
	
	public ActionPossibility isAuthorized(ApplicationUser user) {
		if(user == null) {
			return ActionPossibility.forFalse("Unatuhenticated", HttpStatus.UNAUTHORIZED);
		}
		boolean possible = user.getName().equals(monitorUsername);
		return new ActionPossibility(possible, "Unauthorized", HttpStatus.FORBIDDEN); 
	}
}
