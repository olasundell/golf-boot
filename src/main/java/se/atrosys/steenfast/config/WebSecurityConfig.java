package se.atrosys.steenfast.config;

import org.springframework.beans.factory.annotation.Autowired;
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
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO re-enable, this is very important!
		http.csrf().disable();

		http.authorizeRequests()
			.anyRequest()
				.permitAll()
			.and().formLogin()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/tournaments")
				.loginPage("/login")
				.passwordParameter("password")
				.usernameParameter("username")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/tournaments")
				.permitAll()
		// TODO re-add remember me
//				.and().rememberMe().tokenRepository(persistentTokenRepository())
		;
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.inMemoryAuthentication()
//				.withUser("user").password("password").roles("USER");
		auth.inMemoryAuthentication().withUser("ola@foo.bar").password("password").roles("USER");
	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("ola@foo.bar").password("password").roles("USER");
//	}
}
