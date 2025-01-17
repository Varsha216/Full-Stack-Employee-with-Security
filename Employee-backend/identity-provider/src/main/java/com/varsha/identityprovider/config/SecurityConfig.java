package com.varsha.identityprovider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.varsha.identityprovider.filter.JwtRequestFilter;
import com.varsha.identityprovider.service.AdminDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	// Configuring HttpSecurity 
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http
        		.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth 
//                		.requestMatchers(new AntPathRequestMatcher("/auth/welcome","/auth/authenticate")).permitAll()
                		.requestMatchers("/auth/welcome","/auth/register", "/auth/authenticate", "/auth/validate").permitAll()
                		.anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .authenticationProvider(authenticationProvider()) 
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); 
    } 
  
	@Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsService()); 
        authenticationProvider.setPasswordEncoder(passwordEncoder()); 
        return authenticationProvider; 
    } 
  
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
	
    //User creation
  	@Bean
  	public UserDetailsService userDetailsService() {
  		return new AdminDetailsService();
  	}
    
    // Password Encoding 
    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    } 
//    
//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//       return ServerCodecConfigurer.create();
//    }
    
}
