package com.ariadnext.idcheck.exercise.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ariadnext.idcheck.exercise.dto.HolderDetail;
import com.ariadnext.idcheck.exercise.dto.UserProfile;
import com.ariadnext.idcheck.exercise.exception.FunctionalException;

@Service
public class ConsistencyCheckService {
	
	private static final Logger log = LogManager.getLogger();

	public void checkUserName(HolderDetail holderDetail, UserProfile userProfile) {
		String nameUpperCase = userProfile.getName().toUpperCase();
		if (nameUpperCase.indexOf(holderDetail.getFirstName().get(0)) == -1
				|| nameUpperCase.indexOf(holderDetail.getLastName().get(0)) == -1) {
			String errorMessage = String.format("Le nom du compte Twitter \"%s\" ne correspond pas au nom de la pièce d'identité \"%s %s\"",userProfile.getName(), String.join(" ",holderDetail.getFirstName()), String.join(" ", holderDetail.getLastName()));
			log.error(errorMessage);
			throw new FunctionalException(errorMessage);
		}
		
	}
	
	

}
