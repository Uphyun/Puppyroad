
  package com.puppyroad.app.security.config;
  
  import org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import
  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
  org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;
  
  @Configuration 
  @EnableWebSecurity
  public class SecurityConfig {
  
	  @Bean 
	  PasswordEncoder passwordEncoder() { 
		  return new BCryptPasswordEncoder();
	  } 
	  
	// 인증 및 인가
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			// SecurityFilterChain 빈(bean)을 정의하고, HttpSecurity 객체를 사용하여 시큐리티 구성을 설정
			http.authorizeHttpRequests((authorize) -> authorize
					// FORWARD 타입의 DispatcherType에 대한 요청은 인증 없이 접근을 허용합니다.
					.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()

					// "/"와 "/all" 경로에 대한 요청은 인증 없이 접근을 허용합니다.
					.requestMatchers("/memberInsert", "/memberLogin", "/assets/**", "/main/**", "/login", "/**").permitAll()

					// "/user/**" 경로에 대한 요청은 "USER, ADMIN" 역할을 가진 사용자만 접근
					.requestMatchers("/").hasAnyRole("봉사자", "도그워커","의뢰인")
					//.requestMatchers("/").hasRole("A1")
					// "/admin/**" 경로에 대한 요청은 "ROLE_ADMIN" 권한을 가진 사용자만 접근
					.requestMatchers("/admin/**").hasAuthority("ROLE_관리자")

					// 위에서 명시된 경로들을 제외한 나머지 모든 요청은 인증된 사용자만 접근
					.anyRequest().authenticated())
					// 폼 기반 로그인 설정을 추가합니다.
					.formLogin(formlogin -> formlogin // 로그인 데이터가 자동으로 뜨는거
							//내가 만든 로그인 페이지
							.loginPage("/memberLogin")
					
							// 로그인 성공 시 기본적으로 "/" 경로로 리다이렉트됩니다.
							.usernameParameter("userId")
							.passwordParameter("userPw")
							.defaultSuccessUrl("/", true)
							.failureUrl("/memberLogin?error=true")) // 로그인 실패 시 이동할 경로
					// 로그아웃 설정을 추가합니다.
					.logout(logout -> logout
							// 로그아웃 성공 시 "/all" 경로로 리다이렉트됩니다.
							.logoutSuccessUrl("/memberLogin")
							// 로그아웃 시 세션을 무효화하여 사용자 세션 정보를 삭제합니다.
							.invalidateHttpSession(true)	
					);

			// 설정된 HttpSecurity 객체를 기반으로 SecurityFilterChain을 빌드하여 반환합니다.
			http.csrf(csrf -> csrf.disable());
			return http.build();
		}
  }
 