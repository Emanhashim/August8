//package com.bazra.usermanagement.forgotpassword;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.bazra.usermanagement.model.Roles;
//import com.bazra.usermanagement.model.UserInfo;
//import com.bazra.usermanagement.model.UserInfoService;
//import com.bazra.usermanagement.model.UserRepository;
//
//@Service
//@Transactional
//public class FPService {
//	
//	    @Autowired
//	    private UserRepository userRepository2;
//	    
//	    @Autowired
//	    private UserInfoService userInfoService;
//	 
//	    public String updateResetPasswordToken(String token, String username) throws UsernameNotFoundException {
//	        Boolean e = userRepository2.findByUsername(username).isPresent();
//	        
//	        if (e) {
//	        	UserInfo customer= userRepository2.findByUsername(username).get();
//	        	customer.setRoles(Roles.ADMIN);
////	        	UserInfo user = new UserInfo(customer.getUsername(),customer.getPassword(),customer.getRoles().name());
//	        	
//	
//	            userRepository2.save(customer);
//	        } else {
//	        	throw new UsernameNotFoundException("username not found " + username);
//	        }
//	        return "reset token updated";
//	    }
//	     
//	    public UserInfo getByResetPasswordToken(String token) {
//	    	UserInfo userInfo=userRepository2.findByResetPasswordToken(token).get();
//	    	
//	        return userInfo;
//	    }
//	     
//	    public void updatePassword(UserInfo customer, String newPassword) {
//	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	        String encodedPassword = passwordEncoder.encode(newPassword);
//	        customer.setPassword(encodedPassword);
//	         
//	        customer.setResetPasswordToken(null);
//	        userRepository2.save(customer);
//	    }
//	}
//
//
