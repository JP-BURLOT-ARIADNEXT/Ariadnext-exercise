package com.ariadnext.idcheck.exercise.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

@Configuration
public class TwitterConfig {
	
    @Autowired
    private Environment environment;
    
    @Bean
    public TwitterConnectionFactory twitterConnectionFactory() {
    	return new TwitterConnectionFactory(
                environment.getProperty("spring.social.twitter.appId"),
                environment.getProperty("spring.social.twitter.appSecret"));
    }
    
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory(twitterConnectionFactory());
        return registry;
    }

    
    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
    	return new InMemoryUsersConnectionRepository(connectionFactoryLocator());
    }

}
