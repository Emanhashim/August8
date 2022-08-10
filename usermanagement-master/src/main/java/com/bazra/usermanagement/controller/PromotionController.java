package com.bazra.usermanagement.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.Promotion;
import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.PromotionRepository;
import com.bazra.usermanagement.request.Accountrequest;
import com.bazra.usermanagement.request.CreatePromotionRequest;
import com.bazra.usermanagement.response.AccountResponse;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.SuccessMessageResponse;
import com.bazra.usermanagement.service.UserInfoService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin("*")
@RequestMapping("/Api/Promotion")
public class PromotionController{
	@Autowired
	PromotionRepository promotionRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@Value("${promotion.upload.path}")
	private String promophotoPath;
	
	@PostMapping("/CreatePromotion")

	public ResponseEntity<?> createPromotion(@ModelAttribute CreatePromotionRequest createPromotionRequest, Authentication authentication) throws IOException {
		Optional<Promotion> promotion = promotionRepository.findByTitle(createPromotionRequest.getTitle());
		Account adminAccount= accountRepository.findByAccountNumberEquals(authentication.getName()).get();
		
		if (!adminAccount.getType().matches("ADMIN")) {
			return ResponseEntity.badRequest().body(new ResponseError("Unauthorized request"));
		}
		if (promotion.isPresent()) {
			return ResponseEntity.badRequest().body(new ResponseError("Promotion exists!"));
		}
		String photoName = StringUtils.cleanPath(createPromotionRequest.getPicture().getOriginalFilename());
		String photouploadDir = promophotoPath + createPromotionRequest.getTitle();
		Promotion promo = new Promotion(createPromotionRequest.getTitle(),photoName, createPromotionRequest.getDescription(), createPromotionRequest.getExpirationDate(), createPromotionRequest.isStatus());
		promotionRepository.save(promo);
		UserInfoService.savePhoto(photouploadDir, photoName, createPromotionRequest.getPicture());
	     return ResponseEntity.ok(new SuccessMessageResponse("Created Promotion successfully!!"));
	}
	
	@GetMapping("/GetPromotion")

	public ResponseEntity<?> getAccount(@RequestBody CreatePromotionRequest createPromotionRequest, Authentication authentication) throws IOException {
		return ResponseEntity.badRequest().body(new ResponseError("Created Promotion successfully!!"));
	}

}
