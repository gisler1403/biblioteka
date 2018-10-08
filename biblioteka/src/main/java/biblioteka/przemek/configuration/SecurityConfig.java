package biblioteka.przemek.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "przemek.spring.security")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource getDataSourceSecurity;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(getDataSourceSecurity);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/bookUser/**").hasRole("USER")
				.antMatchers("/bookAdmin/**").hasRole("ADMIN").and()
				.formLogin().loginPage("/customLogin").loginProcessingUrl("/authenticationForm").permitAll().and()
				.logout().logoutSuccessUrl("/").permitAll().and().exceptionHandling().accessDeniedPage("/customDenied");

	}

}
