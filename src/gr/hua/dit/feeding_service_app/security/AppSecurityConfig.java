package gr.hua.dit.feeding_service_app.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(10); /*
																10 is the default value for rounds according to 
		 														the documentation. I assign the value just for reference
		 														when testing w/ bcrypt calculators etc...	
	 															*/
		return encoder;
	}

	//Authentication manager through DB
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where username=?");

	}
	
	//Basic security configuration
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//Add filter for supporting utf-8 encoding on POST requests 
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
    	filter.setEncoding("UTF-8");
    	filter.setForceEncoding(true);
    	http.addFilterBefore(filter, CsrfFilter.class);
    	
        http.authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin()//.loginPage("/login")	//TODO Comment this out for custom login form
        .loginProcessingUrl("/authUser")
        .permitAll()
        //TODO Comment this out for custom 403 page when implemented
//            .and()
//            .exceptionHandling()
//			.accessDeniedPage("/403")
        .and()
        .logout().permitAll();
    }
    
    //Allow unauthenticated access for home page, resources and api access
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/");
		
		web.ignoring().antMatchers("/resources/**");

		web.ignoring().antMatchers("/api/**");
		//Comment this out to exclude /clerk from security check
//		web.ignoring().antMatchers("/clerk/**");

	}

}
