package com.example.book.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.book.model.User;
import com.example.book.repository.UserRepository;


@Service
public class PrincipalDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private EmployeeRepository employeeRepository;
	
	// (중요) 여기서 username은 loginForm.html의 name(입력값=username)과 동일해야한다.
	// 만약 다른 name값을 가져오고 싶으면 시큐리티 설정에 .usernameParameter("다른 name명")을 추가해야한다
	// 함수 종료 시, @AuthenticationPrincipal 애노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("username : " + username);
		
		// findByUsername은 Jpa가 들고있는 기본 CRUD가 아니기 때문에 만들어야한다.
		//Member memberEntity = memberRepository.findByUsername(username);
		User userEntity = userRepository.findByUsername(username);
		
		if(username != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
		
		// 여기까지 완료되면 
		// Security Session - Authentication - UserDetails에서
		// Security Session(Authentication(UserDetails))
		// PrincipalDetails(UserDetails)가 return되면서 
		// Security Session안에 Authentication안에 UserDetails가 등록된다
		// 로그인 완료
	}
}
