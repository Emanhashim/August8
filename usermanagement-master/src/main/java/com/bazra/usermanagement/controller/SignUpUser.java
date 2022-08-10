package com.bazra.usermanagement.controller;

import com.bazra.usermanagement.model.Role;
import com.bazra.usermanagement.model.Roles;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.RoleRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.AdminSignupRequest;
import com.bazra.usermanagement.request.AgentSignUpRequest;
import com.bazra.usermanagement.request.MasterSignupRequest;
import com.bazra.usermanagement.request.MasterSignupRequest2;
import com.bazra.usermanagement.request.SignUpRequest;
import com.bazra.usermanagement.service.SignUpService;
import com.bazra.usermanagement.service.UserInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * SignUp Controller
 * 
 * @author Bemnet
 * @version 4/2022
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/Api")
@Api(value = "Signup User Endpoint", description = "HERE WE TAKE USER'S DATA TO REGISTER TO BAZRA WALLET")
@ApiResponses(value ={
        @ApiResponse(code = 404, message = "web user that a requested page is not available "),
        @ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
        @ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
        @ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
        @ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
@AllArgsConstructor

public class SignUpUser {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SignUpService signUpService;
    @Autowired
    RoleRepository roleRepository;
    
//    public SignUpUser(SignUpService signUpService) {
//    	this.signUpService=signUpService;
//    }
    /**
     * Handles user SignUp request
     * 
     * @param request( user input)
     * @return 
     * @return 
     * @return signup validation response
     */
    @PostMapping("/SignUp/Admin")

    @ApiOperation(value ="SIGNUP ADMIN WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> registerAdmin(@RequestBody AdminSignupRequest request) {
		
    	 return signUpService.adminSignup(request);
    }
    
    @PostMapping("/SignUp/Agent")

    @ApiOperation(value ="SIGNUP AGENT WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> registerAgent(@RequestBody AgentSignUpRequest request) {
		
    	 return signUpService.signUpAgent(request);
    }
    @PostMapping("/SignUp/User")

    @ApiOperation(value ="SIGNUP USER WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest request) {
		
    	 return signUpService.signUpUser(request);
    }
    @PostMapping("/SignUp/Merchant")

    @ApiOperation(value ="SIGNUP MERCHANT WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> registerMerchant(@RequestBody AgentSignUpRequest request) {
		
    	 return signUpService.signUpMerchant(request);
    }
    @PostMapping("/SignUp/Master")

    @ApiOperation(value ="SIGNUP MASTER AGENT WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> signUpMaster(@RequestBody MasterSignupRequest request) {
		
    	 return signUpService.signUpMaster(request);
    }
    
    @PostMapping("/SignUp/Master2")

    @ApiOperation(value ="SIGNUP MASTER WITH REQUIRED CREDENTIALS")
    public ResponseEntity<?> signUpMaster2(@RequestPart("request") MasterSignupRequest2 request,@RequestParam("TIN") MultipartFile tin,@RequestParam("Tread") MultipartFile tread, Authentication authentication) throws IOException {
		
    	 return signUpService.signUpMaster2(request,authentication,tin,tread);
    }
    
//    @PostMapping("/signup/master3")
//    public ResponseEntity<?> signUpMaster3(@RequestPart("request") String request,@RequestParam("TIN") MultipartFile tin,@RequestParam("Tread") MultipartFile tread, Authentication authentication) throws IOException {
//		
//    	 return signUpService.signUpMaster2(request,authentication,tin,tread);
//    }
        
    
}
