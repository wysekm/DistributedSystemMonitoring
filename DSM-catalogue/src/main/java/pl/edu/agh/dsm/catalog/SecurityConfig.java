package pl.edu.agh.dsm.catalog;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${monitor.username}")
	String monitorUsername;
	
	@Value("${monitor.password}")
	String monitorPassword;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password("user").roles("USER").and()	// TODO deletme - it is for test only
			.withUser(monitorUsername).password(monitorPassword).roles("USER", "MONITOR");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.requestMatchers(
				new AntPathRequestMatcher("/measurements", "POST"), 
				new AntPathRequestMatcher("/measurements/*", "DELETE")).authenticated().
			anyRequest().anonymous().and().httpBasic();
		http.csrf().disable();
	}
	
	
}
