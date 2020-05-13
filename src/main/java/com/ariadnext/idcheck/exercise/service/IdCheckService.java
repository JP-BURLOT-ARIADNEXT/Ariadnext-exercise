package com.ariadnext.idcheck.exercise.service;

import java.util.Base64;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ariadnext.idcheck.exercise.config.IdCheckConfig;
import com.ariadnext.idcheck.exercise.dto.ImageAnalysis;

@Service
public class IdCheckService {
	
	private static final Logger log = LogManager.getLogger();

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IdCheckConfig config;
	
	public ImageAnalysis buildImageAnalysis(byte[] imgFront, byte[] imgBack) {
		ImageAnalysis img = new ImageAnalysis();
		img.setFrontImage(Base64.getEncoder().encodeToString(imgFront)); 
		img.setBackImage(Base64.getEncoder().encodeToString(imgBack)); 
		
		return img;
	}
	
	public Object sendImage(ImageAnalysis img) {
		
		String url = config.getRootUrl() + config.getImageAnalysisUrl();
		HttpEntity<Object> entity = new HttpEntity<>(img, getHeaders());
		
		try {
			return restTemplate.exchange(url, HttpMethod.POST, entity, Object.class).getBody();
		} catch(HttpClientErrorException e) {
			log.error(e.getMessage(), e);	
			JSONObject json = new JSONObject(e.getResponseBodyAsString());
			return json.get("errorMessage");
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			return e.getMessage();
		}
	}
	
	public boolean checkUser() {
		String url = config.getRootUrl().concat(config.getUserUrl());
		HttpEntity<String> entity = new HttpEntity<>(getHeaders());
		
		try {
			return HttpStatus.OK.equals(restTemplate.exchange(url, HttpMethod.GET, entity, Object.class).getStatusCode());			
		}catch(RestClientException e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}
	
	public boolean checkAppHeatlh() {
		return HttpStatus.OK.equals(restTemplate.getForEntity(config.getRootUrl().concat(config.getHealthUrl()), Object.class).getStatusCode());
	}
	
	private HttpHeaders getHeaders() {
		String plainCreds = config.getLoginUser() + ":" + config.getLoginPassword();
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}
	

}
