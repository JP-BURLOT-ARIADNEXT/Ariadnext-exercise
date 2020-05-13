package com.ariadnext.idcheck.exercise.web.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
    @GetMapping
    public Object session(UsernamePasswordAuthenticationToken user) {
        return user == null ? null : user.getPrincipal();
    }
}
