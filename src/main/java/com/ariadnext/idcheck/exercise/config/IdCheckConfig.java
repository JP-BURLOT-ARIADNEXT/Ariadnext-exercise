package com.ariadnext.idcheck.exercise.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ariadnext.idcheck")
public class IdCheckConfig {
	
	private String rootUrl;
	
	private String healthUrl;
	
	private String userUrl;
	
	private String imageAnalysisUrl;
	
	private String loginUser;
	
	private String loginPassword;

	public String getRootUrl() {
		return rootUrl;
	}

	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}

	public String getHealthUrl() {
		return healthUrl;
	}

	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getImageAnalysisUrl() {
		return imageAnalysisUrl;
	}

	public void setImageAnalysisUrl(String imageAnalysisUrl) {
		this.imageAnalysisUrl = imageAnalysisUrl;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
