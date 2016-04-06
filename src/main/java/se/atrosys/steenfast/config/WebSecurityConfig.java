package se.atrosys.steenfast.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * TODO write documentation
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO re-enable, this is very important!
		http.csrf().disable();

		http.authorizeRequests()
				.antMatchers(".*")
				.permitAll();

		http.formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/tournaments")
				.loginPage("/login")
				.permitAll()
		// TODO re-add remember me
//				.and().rememberMe().tokenRepository(persistentTokenRepository())
		;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}
}
