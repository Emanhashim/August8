//package com.bazra.usermanagement.forgotpassword;
//
//import java.io.UnsupportedEncodingException;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import javax.management.loading.PrivateClassLoader;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.query.Param;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bazra.usermanagement.model.UserInfo;
//import com.bazra.usermanagement.model.UserInfoService;
//import com.bazra.usermanagement.model.UserRepository;
//import com.bazra.usermanagement.signup.UpdateRequest;
//
//import net.bytebuddy.utility.RandomString;
//
//@RestController
//public class FPController implements UserDetailsService{
//	@Autowired
//	private FPService FPService;
//	
//	@Autowired
//    private JavaMailSender mailSender;
//	
//	@Autowired
//	private UserRepository userRepository;
//	
//////	@Autowired
////	private UpdateRequest uRequest;
//	
//	@Autowired
//	private UserInfoService userInfoService;
//	
//	@RequestMapping(value = "/forgot_password", method = RequestMethod.GET)
//	public String showForgotPasswordForm() {
//	    return "forgot_password_form";
//	}
//	
//	@RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
//	public String processForgotPassword(HttpServletRequest request,Model model) throws UnsupportedEncodingException, MessagingException {
//	    String username = request.getParameter("username");
//	    String token = RandomString.make(30);
//	    String x="";
//////	     
//	    try {
//	        x=FPService.updateResetPasswordToken(token, username);
//	        String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
//	        sendEmail(username, resetPasswordLink);
//	        model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
//	         
//	    } catch (UsernameNotFoundException ex) {
//	        model.addAttribute("error", ex.getMessage());
//	    } 
//	    catch (UnsupportedEncodingException | MessagingException e) {
//	        model.addAttribute("error", "Error while sending email");
//	    }
////	         
//	    return x;
//	}
//	public void sendEmail(String recipientEmail, String link)
//	        throws MessagingException, UnsupportedEncodingException {
//	    MimeMessage message = mailSender.createMimeMessage();              
//	    MimeMessageHelper helper = new MimeMessageHelper(message);
//	     
//	    helper.setFrom("contact@shopme.com", "Shopme Support");
//	    helper.setTo(recipientEmail);
//	     
//	    String subject = "Here's the link to reset your password";
//	     
//	    String content = "<p>Hello,</p>"
//	            + "<p>You have requested to reset your password.</p>"
//	            + "<p>Click the link below to change your password:</p>"
//	            + "<p><a href=\"" + link + "\">Change my password</a></p>"
//	            + "<br>"
//	            + "<p>Ignore this email if you do remember your password, "
//	            + "or you have not made the request.</p>";
//	     
//	    helper.setSubject(subject);
//	     
//	    helper.setText(content, true);
//	     
//	    mailSender.send(message);
//	}
//	@GetMapping("/reset_password")
//	public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
//	    UserInfo customer = FPService.getByResetPasswordToken(token);
//	    model.addAttribute("token", token);
//	     
//	    if (customer == null) {
//	        model.addAttribute("message", "Invalid Token");
//	        return "message";
//	    }
//	     
//	    return "reset_password_form";
//	}
//	@PostMapping("/reset_password")
//	public String processResetPassword(HttpServletRequest request, Model model) {
//	    String token = request.getParameter("token");
//	    String password = request.getParameter("password");
//	     
//	    UserInfo customer = FPService.getByResetPasswordToken(token);
//	    model.addAttribute("title", "Reset your password");
//	     
//	    if (customer == null) {
//	        model.addAttribute("message", "Invalid Token");
//	        return "message";
//	    } else {           
//	        FPService.updatePassword(customer, password);
//	         
//	        model.addAttribute("message", "You have successfully changed your password.");
//	    }
//	     
//	    return "message";
//	}
//@Override
//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//	return null;
//}
//}
//
