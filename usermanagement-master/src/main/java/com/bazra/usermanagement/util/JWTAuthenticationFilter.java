//package com.bazra.usermanagement.util;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.bazra.usermanagement.model.UserInfo;
//import com.bazra.usermanagement.repository.UserRepository;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//
//
//public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    @Value("${jwt.expirationDateInMs}") 
//    private int jwtExpirationInMs;
//    
//    @Value("${jwt.jwtsecret}")
//    private String SECRET_KEY; 
//    
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    UserRepository userRepository;
//    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req,
//                                                HttpServletResponse res) throws AuthenticationException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        return authentication;
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest req,
//                                            HttpServletResponse res,
//                                            FilterChain chain,
//                                            Authentication auth) throws IOException, ServletException {
//        String email = auth.getName();
//        String principal = auth.getPrincipal().toString();
//        Date expiration = new Date(System.currentTimeMillis() + jwtExpirationInMs);
//        String token = Jwts.builder()
//                .setSubject(principal)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes())
//                .compact();
////        UserInfo loginUser = new UserInfo();
//        UserInfo loginUser = userRepository.findByUsername(email).get();
//        loginUser.setResetPasswordToken(token);
////        String jsonUser = Util.objectToJsonResponseAsString(loginUser, "user");
//        res.addHeader("Authorization", "Bearer " + token);
//        res.setContentType("application/json");
//        res.getWriter().write(loginUser.getUsername());
//        res.getWriter().flush();
//    }
//
//}
