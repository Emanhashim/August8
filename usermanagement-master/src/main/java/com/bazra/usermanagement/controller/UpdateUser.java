package com.bazra.usermanagement.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.RequestedUrlRedirectInvalidSessionStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bazra.usermanagement.model.Levels;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.UpdateRequest;
import com.bazra.usermanagement.request.UserInfoUpdateRequest;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.UpdateResponse;
import com.bazra.usermanagement.service.UserInfoService;


/**
 * Update Controller
 * @author Bemnet
 * @version 4/2022
 *
 */


@RestController
@CrossOrigin("*")
@RequestMapping("/Api/Users")
@Api(value = "Signup User Endpoint", description = "HERE WE TAKE USER'S DATA TO UPDATE THE EXISTING DATA'S")

@ApiResponses(value ={
		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
public class UpdateUser {
	
	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Value("${id.upload.path}")
	private String idPath;
	
	@Value("${photo.upload.path}")
	private String photoPath;
	
	
	/**
	 * Checks 
	 * @param update request
	 * @return status for update request
	 */
	@PostMapping("/Update")

	@ApiOperation(value ="UPDATE PASSWORD BY PUTTING PREVIOUS AND NEW ONE AS REQUIRED")
	public ResponseEntity<?> update(@RequestBody UpdateRequest request) {
		String pho= request.getPassword();
        String pho2=request.getNewPassword();
		String newpassword= passwordEncoder.encode(pho2);
        if(pho.matches(pho2)) {
        	
            return ResponseEntity.badRequest().body(new UpdateResponse("Password same as before chose a different one"));
        }
        
		
		return userInfoService.updatePassword(request,newpassword,pho);
	}
	@PostMapping("/UpdateInfo")

	@ApiOperation(value ="UPDATE USER-INFOS")
	public ResponseEntity<?> update(@RequestBody UserInfoUpdateRequest request,Authentication authentication) {
		Optional<UserInfo> optional = userRepository.findByUsername(authentication.getName());
		if (!optional.isPresent()) {
			return ResponseEntity.badRequest().body(new ResponseError("No user found"));
		}
		UserInfo userInfo2 = optional.get();
		if (request.getBirthDay()!=null) {
			userInfo2.setBirthday(request.getBirthDay());
		}
		if (request.getCity()!=null) {
			userInfo2.setCity(request.getCity());
		}
		if (request.getMotherName()!=null) {
			userInfo2.setMotherName(request.getMotherName());
		}
		if (request.getEmail()!=null) {
			userInfo2.setEmail(request.getEmail());
		}
		if (request.getFirstName()!=null) {
			userInfo2.setFirstName(request.getFirstName());
		}
		if (request.getGender()!=null) {
			userInfo2.setGender(request.getGender());
		}
		if (request.getHouseNo()!=null) {
			userInfo2.setHouseNo(request.getHouseNo());
	
		}
		if (request.getKebeleID()!=null) {
			userInfo2.setKebeleID(request.getKebeleID());
		}
		if (request.getPhoto()!=null) {
			userInfo2.setPhoto(request.getPhoto());
			
		}
		if (request.getLastName()!=null) {
			userInfo2.setLastname(request.getLastName());
		}
		
		if (request.getRegion()!=null) {
			userInfo2.setRegion(request.getRegion());
			
		}
		if (request.getIdType()!=null) {
			userInfo2.setIdType(request.getIdType());
			
		}
		if (request.getSubCity()!=null) {
			userInfo2.setSubCity(request.getSubCity());
		}
		if (request.getIdNumber()!=null) {
			userInfo2.setIdNumber(request.getIdNumber());
			
		}
		if (request.getWoreda()!=null) {
			userInfo2.setWoreda(request.getWoreda());
		}
		if (request.getWoreda()!=null) {
			userInfo2.setWoreda(request.getWoreda());
		}
		if (request.getUsername()!=null) {
			userInfo2.setUsername(request.getUsername());
		}
		userRepository.save(userInfo2);

		return ResponseEntity.ok(new UpdateResponse("Updated successfully"));
	}
	
//	@PostMapping("/UpdateLevel")
//
//	@ApiOperation(value ="LEVEL UP USER BY ADDING PHOTO AND ID")
//	public ResponseEntity<?> updatePhoto(@RequestParam("fileImage") MultipartFile photo,@RequestParam("fileID") MultipartFile id,@RequestParam UserInfoUpdateRequest request,Authentication authentication ) throws IOException {
//		
//		String photoName = StringUtils.cleanPath(photo.getOriginalFilename());
//		String idName = StringUtils.cleanPath(id.getOriginalFilename());
//		String userName = authentication.getName();
//		if(request.getUsername().isEmpty()|| request.getWoreda().isEmpty()|| request.getIdNumber().isEmpty()|| request.getSubCity().isEmpty()|| request.getIdType().isEmpty()|| request.getRegion().isEmpty()|| request.getLastName().isEmpty()|| request.getKebeleID().isEmpty()|| request.getHouseNo().isEmpty()|| request.getFirstName().isEmpty()||request.getGender().isEmpty()|| request.getEmail().isEmpty()|| request.getMotherName().isEmpty()|| photoName.isEmpty()|| idName.isEmpty()||request.getBirthDay().isEmpty()||request.getCity().isEmpty()) {
//			return ResponseEntity.badRequest().body(new ResponseError("Both ID and Photo needed for upgrade!"));
//		}
//		UserInfo userInfo2 = userRepository.findByUsername(userName).get();
//		
//		if (request.getBirthDay()!=null) {
//			userInfo2.setBirthday(request.getBirthDay());
//		}
//		if (request.getCity()!=null) {
//			userInfo2.setCity(request.getCity());
//		}
//		if (request.getMotherName()!=null) {
//			userInfo2.setMotherName(request.getMotherName());
//		}
//		if (request.getEmail()!=null) {
//			userInfo2.setEmail(request.getEmail());
//		}
//		if (request.getFirstName()!=null) {
//			userInfo2.setFirstName(request.getFirstName());
//		}
//		if (request.getGender()!=null) {
//			userInfo2.setGender(request.getGender());
//		}
//		if (request.getHouseNo()!=null) {
//			userInfo2.setHouseNo(request.getHouseNo());
//	
//		}
//		if (request.getKebeleID()!=null) {
//			userInfo2.setKebeleID(request.getKebeleID());
//		}
//		if (request.getPhoto()!=null) {
//			userInfo2.setPhoto(request.getPhoto());
//			
//		}
//		if (request.getLastName()!=null) {
//			userInfo2.setLastname(request.getLastName());
//		}
//		
//		if (request.getRegion()!=null) {
//			userInfo2.setRegion(request.getRegion());
//			
//		}
//		if (request.getIdType()!=null) {
//			userInfo2.setIdType(request.getIdType());
//			
//		}
//		if (request.getSubCity()!=null) {
//			userInfo2.setSubCity(request.getSubCity());
//		}
//		if (request.getIdNumber()!=null) {
//			userInfo2.setIdNumber(request.getIdNumber());
//			
//		}
//		if (request.getWoreda()!=null) {
//			userInfo2.setWoreda(request.getWoreda());
//		}
//		
//		if (request.getUsername()!=null) {
//			userInfo2.setUsername(request.getUsername());
//		}
////		userRepository.save(userInfo2);
//		userInfo2.setPhoto(photoName);
//		userInfo2.setKebeleID(idName);
//		userInfo2.setLevels(Levels.LEVEL_3);
//		
//		userRepository.save(userInfo2);
//		String photouploadDir = photoPath + userInfo2.getUsername();
//		String iduploadDir = idPath + userInfo2.getUsername();
//		
//		UserInfoService.savePhoto(photouploadDir, photoName, photo);
//		UserInfoService.saveID(iduploadDir, idName, id);
//		
//		return ResponseEntity.badRequest().body(new ResponseError("Your Level is updated! Now you are Level 3"));
//	}
//	
	
	
	@PostMapping("/UpdateLevel")

	@ApiOperation(value ="LEVEL UP USER BY ADDING PHOTO AND ID")
	public ResponseEntity<?> updatePhoto(@RequestParam("fileImage") MultipartFile photo,@RequestParam("fileID") MultipartFile id	,Authentication authentication ) throws IOException {
		
		String photoName = StringUtils.cleanPath(photo.getOriginalFilename());
		String idName = StringUtils.cleanPath(id.getOriginalFilename());
		String userName = authentication.getName();
		if(photoName.isEmpty()|| idName.isEmpty()) {
			return ResponseEntity.badRequest().body(new ResponseError("Both ID and Photo needed for upgrade!"));
		}
		
		UserInfo userInfo2 = userRepository.findByUsername(userName).get();
		
		if (!userInfo2.getPhoto().isEmpty() || !userInfo2.getKebeleID().isEmpty()) {
			return ResponseEntity.badRequest().body(new ResponseError("User Already Updated"));
		}
//		userRepository.save(userInfo2);
		userInfo2.setPhoto(photoName);
		userInfo2.setKebeleID(idName);
		userInfo2.setLevels(Levels.LEVEL_3);
		
		userRepository.save(userInfo2);
		String photouploadDir = photoPath + userInfo2.getUsername();
		String iduploadDir = idPath + userInfo2.getUsername();
		
		UserInfoService.savePhoto(photouploadDir, photoName, photo);
		UserInfoService.saveID(iduploadDir, idName, id);
		
		return ResponseEntity.badRequest().body(new ResponseError("Your Level is updated! Now you are Level 3"));
	}


}
