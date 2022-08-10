package com.bazra.usermanagement.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.AdminInfo;
import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.MasterAgentInfo;
import com.bazra.usermanagement.model.MerchantInfo;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.AdminRepository;
import com.bazra.usermanagement.repository.AgentRepository;
import com.bazra.usermanagement.repository.MasterAgentRepository;
import com.bazra.usermanagement.repository.MerchantRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.AdminSigninRequest;
import com.bazra.usermanagement.request.AgentSignInRequest;
import com.bazra.usermanagement.request.MasterSignInRequest;
import com.bazra.usermanagement.request.SignInRequest;
import com.bazra.usermanagement.response.AdminSigninResponse;
import com.bazra.usermanagement.response.AgentSignInResponse;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.SignInResponse;
import com.bazra.usermanagement.response.SigninErrorResponse;
import com.bazra.usermanagement.service.UserInfoService;
import com.bazra.usermanagement.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Signin Controller
 * 
 * @author Bemnet
 * @version 4/2022
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/Api")
@Api(value = "SignIn User Endpoint", description = "HERE WE TAKE USER'S PHONENUMEBR AND PASSWORD TO LOGGEDIN")
@ApiResponses(value = {

		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
public class SignInUser {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInfoService userInfoService;

	private UserInfo userInfo;

	private UserDetails userDetails;
	private AgentInfo agentInfo;
	private AdminInfo adminInfo;
	private MerchantInfo merchantInfo;
	private MasterAgentInfo masterAgentInfo;
	Account account;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private MasterAgentRepository masterAgentRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	/**
	 * Generate authentication token
	 * 
	 * @param authenticationRequest
	 * @return user info plus jwt
	 * @throws AuthenticationException
	 */

	@PostMapping("/SignIn/User")
	@ApiOperation(value ="SIGNIN USER WITH PHONENUMBER AND PASSWORD VALUE")
	public ResponseEntity<?> signinUser(@RequestBody SignInRequest authenticationRequest)
			throws AuthenticationException {
		boolean userExists = userRepository.findByUsername(authenticationRequest.getUsername()).isPresent();
		if (userExists) {

			userDetails = userInfoService.loadUserByUsername(authenticationRequest.getUsername());
			userInfo = userRepository.findByUsername(authenticationRequest.getUsername()).get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword(),
					userInfo.getAuthorities());
			Authentication authentication2 = authenticationManager.authenticate(authentication);
			final String jwt = jwtUtil.generateTokenUser(userInfo);

			userRepository.findByUsername(authenticationRequest.getUsername()).get().setResetPasswordToken(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication2);
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

			try {
				account = accountRepository.findByAccountNumberEquals(userInfo.getUsername()).get();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
			}
			
//            Account account = accountRepository.findByAccountNumberEquals(userInfo.getUsername()).get();
			return ResponseEntity.ok(new SignInResponse(userInfo.getId(), userInfo.getUsername(),
					userInfo.getFirstName(), userInfo.getRoles(), userInfo.getCountry(), account.getBalance(),
					userInfo.getGender(), jwt));

		}

//		if (userExists) {
//
//            userDetails = userInfoService.loadUserByUsername(authenticationRequest.getUsername());
//            userInfo = userRepository.findByUsername(authenticationRequest.getUsername()).get();
//            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//            
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            userDetails = userInfoService.loadUserByUsername(authenticationRequest.getUsername());
//            
//            final String jwt = jwtUtil.generateTokenUser(userInfo);
//            
//            userRepository.findByUsername(authenticationRequest.getUsername()).get().setResetPasswordToken(jwt);
//            userInfo = userRepository.findByUsername(authenticationRequest.getUsername()).get();
//            try {
//            	account = accountRepository.findByAccountNumberEquals(userInfo.getUsername()).get();
//    		} catch (Exception e) {
//    			return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
//    		}
////            Account account = accountRepository.findByAccountNumberEquals(userInfo.getUsername()).get();
//            return ResponseEntity.ok(new SignInResponse(userInfo.getId(),  userInfo.getUsername(), userInfo.getFirstName(), userInfo.getRoles(),
//                    userInfo.getCountry(), account.getBalance(), userInfo.getGender(), jwt));
//            
//        }
		return ResponseEntity.badRequest().body(new SigninErrorResponse("Error: Invalid username or password"));

	}

	@PostMapping("/SignIn/Admin")
	@ApiOperation(value ="SIGNIN ADMIN WITH PHONENUMBER AND PASSWORD VALUE")
	public ResponseEntity<?> signinAdmin(@RequestBody AdminSigninRequest adminSigninRequest)
			throws AuthenticationException {
		boolean userExists = adminRepository.findByUsername(adminSigninRequest.getUsername()).isPresent();
		if (userExists) {
			
			userDetails = userInfoService.loadUserByUsername(adminSigninRequest.getUsername());
			adminInfo = adminRepository.findByUsername(adminSigninRequest.getUsername()).get();
			UsernamePasswordAuthenticationToken
	        authentication = new UsernamePasswordAuthenticationToken(
	        		adminSigninRequest.getUsername(),
	        		adminSigninRequest.getPassword(), adminInfo.getAuthorities());
			Authentication authentication2 =authenticationManager.authenticate(authentication);
			final String jwt = jwtUtil.generateTokenUser(adminInfo);
			
			adminRepository.findByUsername(adminSigninRequest.getUsername()).get();
			SecurityContextHolder.getContext()
					.setAuthentication(authentication2);
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			
			try {
				account = accountRepository.findByAccountNumberEquals(adminInfo.getUsername()).get();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
			}
//            Account account = accountRepository.findByAccountNumberEquals(userInfo.getUsername()).get();
			return ResponseEntity.ok(new AdminSigninResponse(adminInfo.getId(), adminInfo.getUsername(),
					 adminInfo.getRoles(), account.getBalance(), jwt));

		}
		return ResponseEntity.badRequest().body(new SigninErrorResponse("Error: Invalid username or password"));

		}

	@PostMapping("/SignIn/Agent")
	@ApiOperation(value ="SIGNIN AGENT WITH PHONENUMBER AND PASSWORD VALUE")
	public ResponseEntity<?> signinAgent(@RequestBody AgentSignInRequest agentSignInRequest)
			throws AuthenticationException {
		boolean userExists = agentRepository.findByUsername(agentSignInRequest.getUsername()).isPresent();
		if (userExists) {
			agentInfo = agentRepository.findByUsername(agentSignInRequest.getUsername()).get();
			userDetails = userInfoService.loadUserByUsername(agentSignInRequest.getUsername());
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					agentSignInRequest.getUsername(), agentSignInRequest.getPassword(), agentInfo.getAuthorities());
			Authentication authentication2 = authenticationManager.authenticate(authentication);

			SecurityContextHolder.getContext().setAuthentication(authentication2);
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			userDetails = userInfoService.loadUserByUsername(agentSignInRequest.getUsername());
			agentInfo = agentRepository.findByUsername(agentSignInRequest.getUsername()).get();
			final String jwt = jwtUtil.generateTokenAgent(agentInfo);

			try {
				account = accountRepository.findByAccountNumberEquals(agentInfo.getUsername()).get();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
			}

			return ResponseEntity.ok(new AgentSignInResponse(agentInfo.getId(), agentInfo.getUsername(),
					agentInfo.getRoles(), account.getBalance(), jwt));

		}

		return ResponseEntity.badRequest().body(new SigninErrorResponse("Error: Invalid username or password"));
	}

	@PostMapping("/SignIn/Merchant")
	@ApiOperation(value ="SIGNIN MERCHANT WITH PHONENUMBER AND PASSWORD VALUE")
	public ResponseEntity<?> signinMerchant(@RequestBody AgentSignInRequest agentSignInRequest)
			throws AuthenticationException {
		boolean userExists = merchantRepository.findByUsername(agentSignInRequest.getUsername()).isPresent();
		if (userExists) {
			merchantInfo = merchantRepository.findByUsername(agentSignInRequest.getUsername()).get();
			userDetails = userInfoService.loadUserByUsername(agentSignInRequest.getUsername());
//			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//					agentSignInRequest.getUsername(), agentSignInRequest.getPassword()));

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					agentSignInRequest.getUsername(), agentSignInRequest.getPassword(), merchantInfo.getAuthorities());
			Authentication authentication2 = authenticationManager.authenticate(authentication);
//	        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication2);

//        userDetails = userInfoService.loadUserByUsername(agentSignInRequest.getUsername());

			final String jwt = jwtUtil.generateTokenMerchant(userDetails);

			try {
				account = accountRepository.findByAccountNumberEquals(merchantInfo.getUsername()).get();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
			}
//        Account account = accountRepository.findByAccountNumberEquals(merchantInfo.getUsername()).get();
			return ResponseEntity.ok(new AgentSignInResponse(merchantInfo.getId(), merchantInfo.getUsername(),
					merchantInfo.getRoles(), account.getBalance(), jwt));

		}

		return ResponseEntity.badRequest().body(new SigninErrorResponse("Error: Invalid username or password"));
	}

	@PostMapping("/SignIn/Master")

	@ApiOperation(value ="SIGNIN MASTERAGENT WITH PHONENUMBER AND PASSWORD VALUE")
	public ResponseEntity<?> signinMaster(@RequestBody MasterSignInRequest masterSignInRequest)
			throws AuthenticationException {
		boolean userExists = masterAgentRepository.findByuserName(masterSignInRequest.getUsername()).isPresent();
		if (userExists) {
			masterAgentInfo = masterAgentRepository.findByuserName(masterSignInRequest.getUsername()).get();
			System.out.println(masterAgentInfo.getUsername());
			userDetails = userInfoService.loadUserByUsername(masterSignInRequest.getUsername());
//			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//					agentSignInRequest.getUsername(), agentSignInRequest.getPassword()));

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					masterSignInRequest.getUsername(), masterSignInRequest.getPassword(),
					masterAgentInfo.getAuthorities());
			Authentication authentication2 = authenticationManager.authenticate(authentication);
//	        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication2);

//        userDetails = userInfoService.loadUserByUsername(agentSignInRequest.getUsername());

			final String jwt = jwtUtil.generateTokenMerchant(userDetails);

			try {
				account = accountRepository.findByAccountNumberEquals(masterAgentInfo.getPhoneNumber()).get();
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(new ResponseError("Account not found"));
			}
//        Account account = accountRepository.findByAccountNumberEquals(merchantInfo.getUsername()).get();
			return ResponseEntity.ok(new AgentSignInResponse(masterAgentInfo.getId(), masterAgentInfo.getPhoneNumber(),
					masterAgentInfo.getRoles(), account.getBalance(), jwt));

		}

		return ResponseEntity.badRequest().body(new SigninErrorResponse("Error: Invalid username or password"));
	}

}
