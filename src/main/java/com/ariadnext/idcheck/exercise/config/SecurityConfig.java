package com.ariadnext.idcheck.exercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.ariadnext.idcheck.exercise.security.SocialSignInAdapter;
import com.ariadnext.idcheck.exercise.security.TwitterConnectionSignup;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
	        .authorizeRequests()
	            .antMatchers("/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	        .formLogin()
	            .loginPage("/")
	            .permitAll()
	            .and()
	        .logout()
	            .permitAll();
	}
	
	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, TwitterConnectionSignup twitterConnectionSignup) {
	    ((InMemoryUsersConnectionRepository) usersConnectionRepository)
	      .setConnectionSignUp(twitterConnectionSignup);
	      
	    return new ProviderSignInController(
	      connectionFactoryLocator, 
	      usersConnectionRepository, 
	      new SocialSignInAdapter());
	}
	
}
