package com.quiz.portal.online.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

import com.quiz.portal.online.security.jwt.AuthEntryPointJwt;
import com.quiz.portal.online.security.jwt.AuthTokenFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfigurator {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// .csrf(csrf -> csrf.disable())
		http.csrf().disable().exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/faculty/**").authenticated()
						.requestMatchers("/api/student/**").authenticated()
						.requestMatchers("/api/quiz/**").authenticated()
						.requestMatchers("/api/user/**").authenticated()
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/api/test/**").permitAll()
						.requestMatchers("/api/quizrequest/**").permitAll()
						.anyRequest().authenticated());

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public UserDetailsService userDeatailsService() {
		return new UserDetailsServiceImpl();
	}

//	@Bean
//	public CorsConfigurationSource configurationSource() {
//		CorsConfiguration conf = new CorsConfiguration();
//		conf.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//		conf.setAllowedMethods(Arrays.asList("POST", "GET", "PUT"));
//		conf.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
//		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
//		src.registerCorsConfiguration("/**", conf);
//		return src;
//	}
}
