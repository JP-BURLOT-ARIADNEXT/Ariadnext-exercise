package com.ariadnext.idcheck.exercise.dto;

import java.security.Principal;

public class UserProfile implements Principal{

	private String name;
	private String imgUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
