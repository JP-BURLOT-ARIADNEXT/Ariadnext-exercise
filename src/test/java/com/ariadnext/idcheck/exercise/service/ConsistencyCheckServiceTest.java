package com.ariadnext.idcheck.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.ariadnext.idcheck.exercise.dto.HolderDetail;
import com.ariadnext.idcheck.exercise.dto.UserProfile;
import com.ariadnext.idcheck.exercise.exception.FunctionalException;

public class ConsistencyCheckServiceTest {

	private ConsistencyCheckService consistencyCheckService = new ConsistencyCheckService();

	@ParameterizedTest
	@ValueSource(strings = { "Norris Chuck", "Chuck Norris", "RealChuckNorris", "Chuck Ray Norris" })
	public void testCheckUserNameOk(String profileName) {
		HolderDetail holderDetail = new HolderDetail();
		holderDetail.setFirstName(Collections.singletonList("CHUCK"));
		holderDetail.setLastName(Collections.singletonList("NORRIS"));

		UserProfile userProfile = new UserProfile();
		userProfile.setName(profileName);

		consistencyCheckService.checkUserName(holderDetail, userProfile);
	}

	@ParameterizedTest
	@ValueSource(strings = { "Robert De Niro", "Chuck Nourris", "C. Norris", "Carlos Ray Norris" })
	public void testCheckUserNameKo(String profileName) {
		HolderDetail holderDetail = new HolderDetail();
		holderDetail.setFirstName(Collections.singletonList("CHUCK"));
		holderDetail.setLastName(Collections.singletonList("NORRIS"));

		UserProfile userProfile = new UserProfile();
		userProfile.setName(profileName);

		FunctionalException thrown = assertThrows(FunctionalException.class, () -> {
			consistencyCheckService.checkUserName(holderDetail, userProfile);
		});

		assertEquals(
				"Le nom du compte Twitter \"" + profileName
						+ "\" ne correspond pas au nom de la pièce d'identité \"CHUCK NORRIS\"",
				thrown.getMessage());
	}
}
