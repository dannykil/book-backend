package com.example.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.model.RoleType;
import com.example.book.model.User;
import com.example.book.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌 = IoC를 해준다 = 메모리에 대신 띄워준다
// service가 왜 필요한가
// 1) 트랜잭션 관리(layer를 알아야한다 : Controller, Service, Dao) 
// 2) 서비스 의미때문
// Repository는 단순히 crud 하나씩 들고있으면
// Service는 예를들어 update가 1개일수도 있고 2개일수도 있다.
// 즉, Service는 update 2개(ex)가 모두 성공해야 이상이 없는 것이며, 하나라도 이상이 있을 경우 롤백을 해야한다.
// 이 때 각각의 update를 트랜잭션이라고 하는데 이 트랜잭션들을 하나의 트랜잭션으로 묶어서 서비스화 할 수 있다.
// Dummy에서도 insert하는데 왜 또 굳이?
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// 먼저 SecurityConfig에서 Bean에 등록해야함
	// 안하면 빌드 시 아래 오류발생
	// Field encoder in com.example.book.service.UserService required a bean of type 'org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder' that could not be found.
	@Autowired
	private BCryptPasswordEncoder encoder;	
	
	@Transactional
	public void insertUser(User user) {
		System.out.println("userService : 회원가입 호출됨");
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);

		// 비밀번호 암호화를 안해도 회원가입은 되지만 시큐리티로 로그인은 안된다.
		user.setPassword(encPassword);
		user.setRole(RoleType.ROLE_EMPLOYEE);
		
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getPersonCode());
		System.out.println(user.getEmail());
		System.out.println(user.getRole());
		
		userRepository.save(user);
		
//		try {
//			userRepository.save(user);
//			return 1;
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println("UserService : 회원가입() : " + e.getMessage());
//		}
//		return -1;
	}	
}
