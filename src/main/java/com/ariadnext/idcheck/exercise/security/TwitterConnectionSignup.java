package com.ariadnext.idcheck.exercise.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service
public class TwitterConnectionSignup  implements ConnectionSignUp  {
	
    @Override
    public String execute(Connection<?> connection) {
    	Twitter twitterApi = (Twitter)connection.getApi();
        
        return twitterApi.userOperations().getUserProfile().getName();
    }
    

}
