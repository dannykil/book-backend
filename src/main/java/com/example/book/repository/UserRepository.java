package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.User;

// Repository는 DAO라고 생각하면 된다.
// 자동으로 Bean 등록돼서(스프링IoC) 필요한 곳에서 인젝션해서 사용할 수 있다.
// @Repository 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	// Jpa는 findAll이라는 함수를 가지고 있는데 
	// 이것은 JpaRepository<Member, Integer>에서 선언했던
	// Member테이블의 모든값을 가지고 오라는 의미이다.
	// JpaRepository에 마우스 우클릭해서 들어가보면 여려가지가 있음
	// findAll, findAllById, saveAll, flush, saveAndFlush, 
	// saveAllAndFlush, deleteInBatch, deleteAllInBatch, 
	// deleteAllByIdInBatch, deleteAllInBatch, getOne, getById, 
	// getReferenceById
	public User findByUsername(String username);
	
	//Optional<Member> findByPersonName(String personName);
}