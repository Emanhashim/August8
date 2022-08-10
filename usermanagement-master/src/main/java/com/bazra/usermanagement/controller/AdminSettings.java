package com.bazra.usermanagement.controller;

import java.util.List;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.MerchantInfo;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.AgentRepository;
import com.bazra.usermanagement.repository.MerchantRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.CreateSettingRequest;
import com.bazra.usermanagement.request.SettingRequest;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.TotalResponseAgents;
import com.bazra.usermanagement.response.TotalResponseCustomers;
import com.bazra.usermanagement.response.TotalResponseMerchants;
import com.bazra.usermanagement.service.SettingService;

@RestController
@CrossOrigin("*")
@RequestMapping("/Settings")
@Api(value = "Admin Setting ", description = "HERE OUR ADMIN OR BAZRA WALLET HAS AN ACCESS FOR BELOW ACTIVITIES")
@ApiResponses(value ={
		@ApiResponse(code = 404, message = "web user that a requested page is not available "),
		@ApiResponse(code = 200, message = "The request was received and understood and is being processed "),
		@ApiResponse(code = 201, message = "The request has been fulfilled and resulted in a new resource being created "),
		@ApiResponse(code = 401, message = "The client request has not been completed because it lacks valid authentication credentials for the requested resource. "),
		@ApiResponse(code = 403, message = "Forbidden response status code indicates that the server understands the request but refuses to authorize it. ")

})
public class AdminSettings {
	@Autowired
	SettingService settingService;

	
	@Autowired
	AgentRepository agentRepository;
	
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	MerchantRepository merchantRepository;
	
	@PostMapping("/SetSetting")
	@ApiOperation(value ="WE CAN CREATE SETTING OR SET SETTING AS AN ADMIN")
    public ResponseEntity<?> setSetting(@RequestBody SettingRequest settingRequest,Authorization authorization) {
    	
        return settingService.setEntity(settingRequest);
    }
	
	@PostMapping("/CreateSetting")
	@ApiOperation(value ="WE CAN CREATE SETTING OR SET SETTING AS AN ADMIN")
    public ResponseEntity<?> createSetting(@RequestBody CreateSettingRequest createSettingRequest,Authorization authorization) {
    	
        return settingService.createSetting(createSettingRequest);
    }
	
	@GetMapping("/TotalAgents")
	@ApiOperation(value ="WE CAN CHECK OR GET TOTAL NUMBER OF AGENTS INSIDE BAZRA WALLET")
    public ResponseEntity<?> getNumberOfAgents(Authentication authentication) {
		Account adminaccount = accountRepository.findByAccountNumberEquals(authentication.getName()).get();
		if (!adminaccount.getType().matches("ADMIN")) {
			return ResponseEntity.badRequest().body(new ResponseError("Unauthorized request"));

		}
		if ( agentRepository.findAll().size()==0) {
			return ResponseEntity.badRequest().body(new ResponseError("No Registered Agent"));
		}
		List<AgentInfo> agentInfo = agentRepository.findAll();
		return ResponseEntity.ok(new TotalResponseAgents(agentInfo.size(),"Total number of registered agents: "+agentInfo.size()));
        
    }
	
	@GetMapping("/TotalCustomers")
	@ApiOperation(value ="WE CAN CHECK OR GET TOTAL NUMBER OF CUSTOMERS INSIDE BAZRA WALLET")
    public ResponseEntity<?> getNumberOfCustomers(Authentication authentication) {
		Account adminaccount = accountRepository.findByAccountNumberEquals(authentication.getName()).get();
		if (!adminaccount.getType().matches("ADMIN")) {
			return ResponseEntity.badRequest().body(new ResponseError("Unauthorized request"));

		}

		
		if (userRepository.findAll().size()==0) {
			return ResponseEntity.badRequest().body(new ResponseError("No Registered User"));
		}
		List<UserInfo> userInfo = userRepository.findAll();
		return ResponseEntity.ok(new TotalResponseCustomers(userInfo.size(),"Total number of registered customers: "+userInfo.size()));

        
    }
	
	@GetMapping("/TotalMerchants")
	@ApiOperation(value ="WE CAN CHECK OR GET TOTAL NUMBER OF MERCHANTS INSIDE BAZRA WALLET")
    public ResponseEntity<?> getNumberOfMerchants(Authentication authentication) {
		Account adminaccount = accountRepository.findByAccountNumberEquals(authentication.getName()).get();
		if (!adminaccount.getType().matches("ADMIN")) {
			return ResponseEntity.badRequest().body(new ResponseError("Unauthorized request"));

		}
		if (merchantRepository.findAll().size()==0) {
			return ResponseEntity.badRequest().body(new ResponseError("No Registered Merchant"));
		}
		List<MerchantInfo> merchanInfo = merchantRepository.findAll();
		return ResponseEntity.ok(new TotalResponseMerchants(merchanInfo.size(),"Total number of registered customers: "+merchanInfo.size()));
        
    }
	
	
	

}
