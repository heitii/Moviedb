package hh.swd20.moviedb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hh.swd20.moviedb.webcontrol.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
    	.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests()
        	.antMatchers("/delete/{id}", "/edit/{id}").hasAuthority("ADMIN")
          .anyRequest().authenticated()
          .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/movielist")
          .permitAll()
          .and()
      .logout()
          .permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
