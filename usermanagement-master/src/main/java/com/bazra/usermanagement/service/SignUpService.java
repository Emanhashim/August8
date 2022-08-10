package com.bazra.usermanagement.service;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bazra.usermanagement.model.Account;
import com.bazra.usermanagement.model.AdminInfo;
import com.bazra.usermanagement.model.AgentInfo;
import com.bazra.usermanagement.model.Levels;
import com.bazra.usermanagement.model.MasterAgentInfo;
import com.bazra.usermanagement.model.MerchantInfo;
import com.bazra.usermanagement.model.UserInfo;
import com.bazra.usermanagement.repository.AccountRepository;
import com.bazra.usermanagement.repository.AdminRepository;
import com.bazra.usermanagement.repository.AgentRepository;
import com.bazra.usermanagement.repository.MasterAgentRepository;
import com.bazra.usermanagement.repository.MerchantRepository;
import com.bazra.usermanagement.repository.UserRepository;
import com.bazra.usermanagement.request.AdminSignupRequest;
import com.bazra.usermanagement.request.AgentSignUpRequest;
import com.bazra.usermanagement.request.MasterSignupRequest;
import com.bazra.usermanagement.request.MasterSignupRequest2;
import com.bazra.usermanagement.request.SignUpRequest;
import com.bazra.usermanagement.response.AdminSignupResponse;
import com.bazra.usermanagement.response.MasterSignupResponse;
import com.bazra.usermanagement.response.ResponseError;
import com.bazra.usermanagement.response.SignUpResponse;

@Service
public class SignUpService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AgentRepository agentRepository;
	@Autowired
	MerchantRepository merchantRepository;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	AccountService accountservice;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MasterAgentRepository masterAgentRepository;
	
	@Value("${tin.upload.path}")
	private String tinPath;
	
	@Value("${tread.upload.path}")
	private String treadPath;

	public ResponseEntity<?> adminSignup(AdminSignupRequest request) {
		boolean userExists1 = adminRepository.findByUsername(request.getUsername()).isPresent();
		
		if (userExists1) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
		}
		String pass1 = request.getPassword();
		

		
		if (accountRepository.findByAccountNumberEquals(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Account already taken"));
		}
		

		if (!userInfoService.checkString(pass1)) {
			return ResponseEntity.badRequest().body(
					new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
		} else if (pass1.chars().filter(ch -> ch != ' ').count() < 8
				|| pass1.chars().filter(ch -> ch != ' ').count() > 15) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
		}

		
		if (request.getUsername().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
		}
		

		
		if ((!userInfoService.checkUsername(request.getUsername()))) {

			return ResponseEntity.badRequest()
					.body(new SignUpResponse("Phone Number Should be Integer Only and Minimum 10 Digit"));
		}
		AdminInfo adminInfo = new AdminInfo( request.getUsername(),
				passwordEncoder.encode(request.getPassword()));
		
		adminInfo.setRoles("ADMIN");
			
			
		
		
		adminRepository.save(adminInfo);
		AdminInfo adminInfo2 = adminRepository.findByUsername(request.getUsername()).get();
		Account account = new Account(adminInfo2.getUsername(), new BigDecimal(1000), adminInfo2.getId(),
				adminInfo2.getUsername(), adminInfo2.getRoles());

		account.setUser_id(adminInfo2.getId());
		account.setUserid(adminInfo2.getId());
		accountRepository.save(account);
		return ResponseEntity.ok(new AdminSignupResponse(adminInfo2.getUsername(),  "Successfully Registered"));
	}
	
	public ResponseEntity<?> signUpUser(SignUpRequest request) {
		boolean userExists1 = userRepository.findByUsername(request.getUsername()).isPresent();
		boolean userExist1 = userRepository.findByEmail(request.getEmail()).isPresent();
		boolean useradminexist = adminRepository.findByUsername(request.getUsername()).isPresent();
		boolean useragentexist = agentRepository.findByUsername(request.getUsername()).isPresent();
		if (userExists1 || useradminexist || useragentexist) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
		} else if (userExist1) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Email already in use!"));
		}
		String pass1 = request.getPassword();
		String pass2 = request.getConfirmPassword();

		if (!pass1.matches(pass2)) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
		}
		if (accountRepository.findByAccountNumberEquals(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Account already taken"));
		}
		

		if (!userInfoService.checkString(pass1)) {
			return ResponseEntity.badRequest().body(
					new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
		} else if (pass1.chars().filter(ch -> ch != ' ').count() < 8
				|| pass1.chars().filter(ch -> ch != ' ').count() > 15) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
		}

		if ((!userInfoService.checkname(request.getFirstName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"First Name Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}
		if ((!userInfoService.checkLastname(request.getLastName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"FatherName Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}
		if (request.getEmail().isBlank()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your email "));
		}
		if (request.getFirstName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your name "));
		}
		if (request.getLastName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your father name "));
		}
		if (request.getUsername().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
		}
		

		if ((!userInfoService.checkBirthdate(request.getBirthDay()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Invalid date value"));
		}
		if ((!userInfoService.checkUsername(request.getUsername()))) {

			return ResponseEntity.badRequest()
					.body(new SignUpResponse("Phone Number Should be Integer Only and Minimum 10 Digit"));
		}
		int compareOneTwo = Character.compare(request.getUsername().charAt(0), '0');  
		if (compareOneTwo == 0) {

			userInfoService.removeFirst(request.getUsername());
			System.out.println(userInfoService.removeFirst(request.getUsername()));
			request.setUsername(userInfoService.removeFirst(request.getUsername()));
		}
		UserInfo userInfo = new UserInfo(request.getFirstName(), request.getLastName(),
				passwordEncoder.encode(request.getPassword()), request.getUsername());
		
		userInfo.setBirthday(request.getBirthDay());
		userInfo.setEmail(request.getEmail());
			
			userInfo.setRoles("USER");
			userInfo.setLevels(Levels.LEVEL_1);
			String strgender = request.getGender();
			if (strgender.matches("MALE")) {

				userInfo.setGender(strgender);

			} else if (strgender.matches("FEMALE")) {

				userInfo.setGender(strgender);
			}
		
		
		userRepository.save(userInfo);
		UserInfo userInfo2 = userRepository.findByUsername(userInfo.getUsername()).get();
		Account account = new Account(userInfo2.getUsername(), new BigDecimal(1000), userInfo2.getId(),
				userInfo2.getUsername(), userInfo2.getRoles());
//		String accountnumber = UserInfoService.NumericString(8);
		
//		account.setBlocked(false);
		account.setUser_id(userInfo2.getId());
		account.setUserid(userInfo2.getId());
		accountRepository.save(account);
		return ResponseEntity.ok(new SignUpResponse(userInfo2.getUsername(), userInfo2.getRoles(),
				userInfo2.getCountry(), userInfo2.getGender(), "Successfully Registered", userInfo2.getFirstName(),
				userInfo2.getLastName(), userInfo.getLevels()));
	}

	public ResponseEntity<?> signUpAgent(AgentSignUpRequest request) {
		boolean userExists1 = agentRepository.findByUsername(request.getUsername()).isPresent();
//        Aagent = accountRepository.findByusername(request.getUsername());
		if (userExists1) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
		}
		String pass1 = request.getPassword();
		String pass2 = request.getConfirmPassword();

		if (!pass1.matches(pass2)) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
		}
		if (accountRepository.findByAccountNumberEquals(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Account already taken"));
		}
	
		if (!userInfoService.checkString(pass1)) {
			return ResponseEntity.badRequest().body(
					new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
		} else if (pass1.chars().filter(ch -> ch != ' ').count() < 8
				|| pass1.chars().filter(ch -> ch != ' ').count() > 15) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
		}

		if ((!userInfoService.checkname(request.getFirstName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"First Name Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}
		if ((!userInfoService.checkLastname(request.getLastName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"FatherName Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}

		if (request.getFirstName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your name "));
		}
		if (request.getLastName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your father name "));
		}
		if (request.getUsername().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
		}
		if (request.getLicenceNumber().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your licence number "));
		}
		if (request.getBusinessLNum().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your business licence number "));
		}

		if ((!userInfoService.checkUsername(request.getUsername()))) {

			return ResponseEntity.badRequest()
					.body(new SignUpResponse("Phone Number Should be Integer Only and Minimum 10 Digit"));
		}

		int compareOneTwo = Character.compare(request.getUsername().charAt(0), '0');  
		if (compareOneTwo == 0) {

			userInfoService.removeFirst(request.getUsername());
			System.out.println(userInfoService.removeFirst(request.getUsername()));
			request.setUsername(userInfoService.removeFirst(request.getUsername()));
		}
		AgentInfo agentInfo = new AgentInfo(request.getFirstName(), request.getLastName(),
				passwordEncoder.encode(request.getPassword()), request.getUsername());
		agentInfo.setLicenceNumber(request.getLicenceNumber());
		agentInfo.setCompanyName(request.getCompanyName());
		agentInfo.setBusinessLNum(request.getBusinessLNum());
		agentInfo.setRoles("AGENT");
		
		agentRepository.save(agentInfo);

		

		
		AgentInfo agentInfo2 = agentRepository.findByUsername(agentInfo.getUsername()).get();

		Account account = new Account(agentInfo2.getUsername(), new BigDecimal(1000), agentInfo2.getId(),
				agentInfo2.getUsername(), agentInfo2.getRoles());
		account.setUser_id(agentInfo2.getId());
		account.setUserid(agentInfo2.getId());
		accountservice.save(account);
		return ResponseEntity.ok(new SignUpResponse(agentInfo2.getUsername(), agentInfo2.getRoles(),
				"Successfully Registered", agentInfo2.getFirstName(), agentInfo2.getLastName()));
	}

	public ResponseEntity<?> signUpMerchant(AgentSignUpRequest request) {
		boolean userExists1 = merchantRepository.findByUsername(request.getUsername()).isPresent();

		if (userExists1) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
		}
		String pass1 = request.getPassword();
		String pass2 = request.getConfirmPassword();

		if (!pass1.matches(pass2)) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Passwords don't match!"));
		}
		if (accountRepository.findByAccountNumberEquals(request.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Account already taken"));
		}
		int compareOneTwo = Character.compare(request.getUsername().charAt(0), '0');  
		if (compareOneTwo == 0) {

			userInfoService.removeFirst(request.getUsername());
			System.out.println(userInfoService.removeFirst(request.getUsername()));
			request.setUsername(userInfoService.removeFirst(request.getUsername()));
		}
		MerchantInfo merchantInfo = new MerchantInfo(request.getFirstName(), request.getLastName(),
				passwordEncoder.encode(request.getPassword()), request.getUsername());

		merchantInfo.setLicenceNumber(request.getLicenceNumber());
		merchantInfo.setCompanyName(request.getCompanyName());
		merchantInfo.setBusinessLNum(request.getBusinessLNum());
		merchantInfo.setRoles("MERCHANT");
		merchantRepository.save(merchantInfo);

		if (!userInfoService.checkString(pass1)) {
			return ResponseEntity.badRequest().body(
					new SignUpResponse("Your password must have atleast 1 number, 1 uppercase and 1 lowercase letter"));
		} else if (pass1.chars().filter(ch -> ch != ' ').count() < 8
				|| pass1.chars().filter(ch -> ch != ' ').count() > 15) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Your password must have 8 to 15 characters "));
		}

		if ((!userInfoService.checkname(merchantInfo.getFirstName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"First Name Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}
		if ((!userInfoService.checkLastname(merchantInfo.getLastName()))) {

			return ResponseEntity.badRequest().body(new SignUpResponse(
					"FatherName Should Start with One Uppercase and LoweCase letter, Minimum input 4 character and  String Character only"));
		}

		if (merchantInfo.getFirstName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your name "));
		}
		if (merchantInfo.getLastName().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your father name "));
		}
		if (merchantInfo.getUsername().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone "));
		}
		if (merchantInfo.getLicenceNumber().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your licence number "));
		}
		if (merchantInfo.getBusinessLNum().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your business licence number "));
		}

		if ((!userInfoService.checkUsername(merchantInfo.getUsername()))) {

			return ResponseEntity.badRequest()
					.body(new SignUpResponse("Phone Number Should be Integer Only and Minimum 10 Digit"));
		}

		merchantRepository.save(merchantInfo);
		MerchantInfo agentInfo2 = merchantRepository.findByUsername(merchantInfo.getUsername()).get();
		Account account = new Account(agentInfo2.getUsername(), new BigDecimal(1000), agentInfo2.getId(),
				agentInfo2.getUsername(), agentInfo2.getRoles());
		accountservice.save(account);
		return ResponseEntity.ok(new SignUpResponse(merchantInfo.getUsername(), merchantInfo.getRoles(),
				"Successfully Registered", merchantInfo.getFirstName(), merchantInfo.getLastName()));
	}

	public ResponseEntity<?> signUpMaster(MasterSignupRequest request) {
		boolean userExists1 = masterAgentRepository.findByphoneNumber(request.getPhone()).isPresent();

		if (userExists1) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Error: Username is already taken!"));
		}

		if (request.getPhone().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your phone number"));
		}

		if (request.getUsername().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your user name"));
		}

		if (request.getEmail().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your email"));
		}

		if (request.getRegion().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your region"));
		}

		if (request.getCity().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your city"));
		}

		if (request.getOrganizationType().isBlank()) {

			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your organization type"));
		}

		
		MasterAgentInfo masterAgentInfo = new MasterAgentInfo(request.getPhone(), request.getUsername(),passwordEncoder.encode(request.getPassword()),
				request.getEmail(), request.getRegion(), request.getCity(), request.getOrganizationType());
		
		if ((!userInfoService.checkUsername(masterAgentInfo.getPhoneNumber()))) {

			return ResponseEntity.badRequest()
					.body(new SignUpResponse("Phone Number Should be Integer Only and Minimum 10 Digit"));
		}

		if (accountRepository.findByAccountNumberEquals(request.getPhone()).isPresent()) {
			return ResponseEntity.badRequest().body(new SignUpResponse("Account already taken"));
		}

		

		masterAgentInfo.setRoles("MASTER");
		masterAgentRepository.save(masterAgentInfo);
		Account account = new Account(masterAgentInfo.getPhoneNumber(), new BigDecimal(1000), masterAgentInfo.getId(),
				masterAgentInfo.getUsername(), masterAgentInfo.getRoles());

		accountservice.save(account);
		
		return ResponseEntity.ok(new MasterSignupResponse("Successfully Registered", masterAgentInfo.getUsername(),
				masterAgentInfo.getRoles(), masterAgentInfo.getEmail(), masterAgentInfo.getOrganizationType()));
		// return ResponseEntity.badRequest().body(new SignUpResponse("Enter your
		// business licence number "));
	}

	public ResponseEntity<?> signUpMaster2(MasterSignupRequest2 request,Authentication authentication, MultipartFile tin, MultipartFile tread) throws IOException {
		boolean userExists1 = masterAgentRepository.findByuserName(authentication.getName()).isPresent();

	  	if (!userExists1) {
	
	          return ResponseEntity.badRequest().body(new SignUpResponse("No such user"));
	  	}
	  	if(!request.getTinNumber().isEmpty()) {
	  		return ResponseEntity.badRequest().body(new SignUpResponse("Already passed this stage"));
	  	}
	  	
	  	MasterAgentInfo masterAgentInfo = masterAgentRepository.findByuserName(authentication.getName()).get();
	  	if (request.getOrganizationName().isBlank()) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your Organization Name"));
		}
	  	
	  	if (request.getOrganizationType().isBlank()) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your organization type"));
		}
	  	
	  	if (request.getBusinessSector().isBlank()) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your business sector"));
		}
	  	
	  	if (request.getBusinessType().isBlank()) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your business type"));
		}
	  	
	  	if (request.getTinNumber().isBlank()) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your tin number"));
		}
	  	
	  	if (request.getCapital().equals(null)) {
	
			return ResponseEntity.badRequest().body(new SignUpResponse("Enter your capital"));
		}
	  	
	  	String tinName = StringUtils.cleanPath(tin.getOriginalFilename());
		String treadName = StringUtils.cleanPath(tread.getOriginalFilename());
		
		if(tinName.isEmpty()) {
			return ResponseEntity.badRequest().body(new ResponseError("Tin Certificate must be attached"));
		}
		if(treadName.isEmpty()) {
			return ResponseEntity.badRequest().body(new ResponseError("Tread Certificate must be attached"));
		}
	  	masterAgentInfo.setOrganizationName(request.getOrganizationName());
	  	masterAgentInfo.setOrganizationType(request.getOrganizationType());
	  	masterAgentInfo.setBusinessSector(request.getBusinessSector());
	  	masterAgentInfo.setBusinessType(request.getBusinessType());
	  	masterAgentInfo.setTinNumber(request.getTinNumber());
	  	masterAgentInfo.setCapital(request.getCapital());
	  	masterAgentInfo.setRegion(request.getRegion());
	  	masterAgentInfo.setCity(request.getCity());
	  	masterAgentInfo.setSubcity(request.getSubcity());
	  	masterAgentInfo.setSpecificLocation(request.getSpecificLocation());
	  	masterAgentInfo.setWoreda(request.getWoreda());
	  	masterAgentInfo.setHouseNumber(request.getHouseNumber());
	  	masterAgentInfo.setPhoneNumber(request.getPhoneNumber());
	  	masterAgentInfo.setTinDocument(tinName);
	  	masterAgentInfo.setTreadDocument(treadName);
	  	masterAgentRepository.save(masterAgentInfo);
	  	
	  	
	  	String tinuploadDir = tinPath + masterAgentInfo.getPhoneNumber();
	  	String treaduploadDir = treadPath + masterAgentInfo.getPhoneNumber();
	  	
	  	
	  	UserInfoService.saveTIN(tinuploadDir, tinName, tin);
		UserInfoService.saveTREAD(treaduploadDir, treadName, tread);
		
		
		return ResponseEntity.ok(new MasterSignupResponse("Successfully finished registration",masterAgentInfo.getUsername(),masterAgentInfo.getRoles(), masterAgentInfo.getEmail(),
				 masterAgentInfo.getOrganizationType()));
	//		return ResponseEntity.badRequest().body(new SignUpResponse("Enter your business licence number "));
		}

}
