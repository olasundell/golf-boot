package se.atrosys.steenfast.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * TODO write documentation
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

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
//		setupInMemAuth(auth);
		setupDbAuth(auth);
	}

	private void setupInMemAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("ola@foo.bar").password("password").roles("USER");
	}

	private void setupDbAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.withDefaultSchema()
				.dataSource(dataSource)
				.withUser("ola@foo.bar")
					.password("password")
					.roles("USER");

//		User user = new User("ola@foo.bar", "password", Collections.singletonList(new SimpleGrantedAuthority("USER")));
//		userDetailsService.createUser(user);
	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("ola@foo.bar").password("password").roles("USER");
//	}
}
