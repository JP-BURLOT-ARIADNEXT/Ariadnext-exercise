package com.ariadnext.idcheck.exercise.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.ariadnext.idcheck.exercise.dto.UserProfile;

@Service
public class SocialSignInAdapter implements SignInAdapter {

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		
        List<GrantedAuthority> roles = new ArrayList<>();
        
        //add the role to the list
        roles.add(new SimpleGrantedAuthority("twitter"));
        
        UserProfile principal = new UserProfile();
        principal.setImgUrl(connection.getImageUrl());
        principal.setName(userId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, roles);
        //put the authentication token in the context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return "/";
	}

}
