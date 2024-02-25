package com.example.book.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.book.model.User;

import lombok.Data;

// 시큐리티가 /loginProc 주소요청을 낚아채서 로그인을 진행시켜주면서 
// 같은 세션공간 안에 시큐리티만의 공간을 가지고 세션을 만들어준다.
// Security Context Holder 
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

	//private Member member;
	private User user;
	
	// 일반 로그인을할 때의 생성자를 만들어준다
//	public PrincipalDetails(Member member) {
//		super();
//		this.member = member;
//		System.out.println("Member : " + member);
//	}
	
	public PrincipalDetails(User user) {
		super();
		this.user = user;
		System.out.println("Employee : " + user);
	}
	
	// 인증 로그인을할 때의 생성자를 만들어준다
	// attributes를 통해서 Member객체를 만들어줄 것 이다.
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		//this.member = member;
		this.attributes = attributes;
	}
	
	//구글에서 가져온 유저정보들이 Map타입으로 저장된다
	// IndexController /test/oauth/login에서 oauth2User.getAttributes()의 
	// getAttributes()에 마우스를 대보면 알 수 있다.
	private Map<String, Object> attributes;
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
//	public String getBrand() {
//		return user.getBrand();
//	}
	
//	public String getBranch() {
//		return user.getBranch();
//	}
	
//	public String getPersonName() {
//		return user.getPersonName();
//	}
	
	public String getPersonCode() {
		return user.getPersonCode();
	}
	
	public String getEmail() {
		//return member.getEmail();
		return user.getEmail();
	}
	// 해당 유저의 권한을 리턴하는 곳
	// 권한정보는 Collection타입으로 가지고 있는데 Collection의 자식객체인 ArrayList를 
	// 이용해서 권한정보를 담을 것이다.
	// ArrayList의 각각의 아이템은 String타입으로 저장할 수 있다.
	@Override
	// GrantedAuthority타입을 가지는 Collection
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// ArrayList는 Collection의 자식이다.
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {

			@Override
			// 각각의 권한은 String타입으로 저장된
			public String getAuthority() {
				//return member.getRole().toString();
				return "";
			}
		});		
		return collect;
	}
	
	@Override
	public String getPassword() {
		//return member.getPassword();
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
//		return user.getPersonName();
	}
	
	@Override
	public String getName() {
		return null;
	}	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
