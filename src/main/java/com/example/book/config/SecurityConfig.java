package com.example.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsConfigurationSource;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration // 1st
@EnableWebSecurity // 2 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true) // 6
//@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 3 override 해주기 위해 extends

	// 비밀번호를 암호화해서 저장하기 위해서 먼저 SecurityConfig에 Bean으로 등록하고
	// MemberService에서 Autowired로 사용한다.
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	// 4 override
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("http >>> " + http);
		// 5 이 부분을 추가했더니 회원가입 시 403 forbidden path error가 해결됨
		http.csrf().disable();
		http.cors().configurationSource(corsConfigurationSource());
		http.authorizeRequests()
		//.antMatchers("/employee/**").authenticated() // 인증이 필요하다. 잠시주석 2023.02.01
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll() // 위 경로에 포함이 안되면 모두 permitAll
		.and()
		.formLogin()
		//.loginPage("/member/loginForm")
		.loginPage("/")
		.loginProcessingUrl("/auth/loginProc") // form action="/loginProc" 지정해주면 시큐리티가 로그인을 진행해준다.
		.defaultSuccessUrl("/login");
	}
	
	// CORS 허용 적용
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

//        configuration.addAllowedOrigin("*");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("http://localhost:3001");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
