package com.example.interviewprep.Security;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.interviewprep.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService CustomUserDetailsService;
    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService CustomUserDetailsService){
        this.jwtService=jwtService;
        this.CustomUserDetailsService=CustomUserDetailsService;
    }
    
    @Override
    protected  void doFilterInternal(HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain) throws ServletException, IOException {

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                System.out.println("JWT Token Not Found");

                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("JWT Token Found");
            String token = authHeader.substring(7);
            System.out.println("token : "+ token);
            String email = jwtService.extractEmail(token);
            System.out.println("email : "+email);
            if(email !=null && SecurityContextHolder.getContext().getAuthentication()==null){
                
                UserDetails userDetails = CustomUserDetailsService.loadUserByUsername(email);
                System.out.println("userDetails : "+userDetails);
                System.out.println("AUTHORITIES : " + userDetails.getAuthorities());


                if(jwtService.isTokenValid(token,userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );
                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(authentication);
                    System.out.println("SecurityContext :"+SecurityContextHolder.getContext().getAuthentication().getName());
                }
            }
            filterChain.doFilter(request, response);
    }
}
