package com.ariadnext.idcheck.exercise.web.rest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ariadnext.idcheck.exercise.dto.AnalysisResponse;
import com.ariadnext.idcheck.exercise.dto.AnalysisResult;
import com.ariadnext.idcheck.exercise.dto.HolderDetail;
import com.ariadnext.idcheck.exercise.dto.UserProfile;
import com.ariadnext.idcheck.exercise.exception.FunctionalException;
import com.ariadnext.idcheck.exercise.service.ConsistencyCheckService;
import com.ariadnext.idcheck.exercise.service.IdCheckService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/idCheck")
public class IdCheckController {

	private static final Logger log = LogManager.getLogger();

	@Autowired
	private IdCheckService idCheckService;

	@Autowired
	private ConsistencyCheckService consistencyCheckService;

	@GetMapping("/health")
	public boolean health() {
		return idCheckService.checkAppHeatlh();
	}

	@GetMapping("/user")
	public boolean user() {
		return idCheckService.checkUser();
	}

	@PostMapping("/analysis")
	public AnalysisResponse analysis(@RequestPart(name = "front") MultipartFile imgFront,
			@RequestPart(name = "back") MultipartFile imgBack,
			UsernamePasswordAuthenticationToken principal) {

		if(imgFront.isEmpty()){
			throw new FunctionalException("Le fichier correspondant au recto de la pièce d'identité est obligatoire");
		}		
		if(imgBack.isEmpty()){
			throw new FunctionalException("Le fichier correspondant au verso de la pièce d'identité est obligatoire");
		}		
		
		if(principal == null) {
			throw new FunctionalException("L'utilisateur n'est pas connecté");
		}		
		UserProfile userProfile = (UserProfile) principal.getPrincipal();

		Object result;
		try {
			result = idCheckService.sendImage(idCheckService.buildImageAnalysis(imgFront.getBytes(), imgBack.getBytes()));
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			throw new FunctionalException(String.format("Une erreur technique est survenue dans la lecture des fichiers (%s)", e.getMessage()));
		}

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		AnalysisResult analysisResult = mapper.convertValue(result, AnalysisResult.class);

		AnalysisResponse response = new AnalysisResponse();
		response.setAnalysisResult(analysisResult);
		
		consistencyCheckService.checkUserName(analysisResult.getHolderDetail(), userProfile);
		
		response.setMessage("Identité confirmée");
		return response;
	}
	
	@ResponseBody
	@ExceptionHandler(value = FunctionalException.class)
	public ResponseEntity<FunctionalException> handleException(FunctionalException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
	}
	

}
