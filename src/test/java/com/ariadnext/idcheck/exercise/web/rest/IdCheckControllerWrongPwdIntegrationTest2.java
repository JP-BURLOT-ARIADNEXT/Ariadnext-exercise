package com.ariadnext.idcheck.exercise.web.rest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.ariadnext.idcheck.exercise.service.IdCheckService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application-integrationtest.properties", properties = {"ariadnext.idCheck.loginPassword=wrong"})
public class IdCheckControllerWrongPwdIntegrationTest2 {
    
    @Autowired 
    private IdCheckService idCheckService;
    
    @Test
    public void testUserWrongPwd() {
    	assertFalse(idCheckService.checkUser());
    }
}