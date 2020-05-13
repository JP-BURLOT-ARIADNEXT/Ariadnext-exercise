package com.ariadnext.idcheck.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ariadnext.idcheck.exercise.config.IdCheckConfig;
import com.ariadnext.idcheck.exercise.dto.ImageAnalysis;

@SpringBootTest
@AutoConfigureMockMvc
public class IdCheckServiceTest {

	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private IdCheckService idCheckService;
	
	@Mock
	private IdCheckConfig idCheckConfig;
	
	@Test
	public void testGetUser() throws Exception {
		when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(), any(Class.class))).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "", "{errorMessage:\"mauvaise requete\"}".getBytes(), Charset.defaultCharset()));
		Object result = idCheckService.sendImage(new ImageAnalysis());
		assertEquals("mauvaise requete", result);
		
	}
}