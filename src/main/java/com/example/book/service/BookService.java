package com.example.book.service;

  import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.model.Book;
import com.example.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 기능을 정의할 수 있고, 트랜잭션을 관리 수 있다 
public class BookService {
	
	private final BookRepository bookRepository;
	
	@Transactional // 서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션 관리하겠다.
	public Book 저장하기(Book book) {
		return bookRepository.save(book);
	}
	
	@Transactional(readOnly=true) // JPA 변경감지라는 내부기능이 활성화 안됨. update 시 정합성 유지해줌.insert의 유령데이터현상(팬텀현상)은 못막음.
	public Book 한건가져오기(Long id) {
		return bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!!"));
	}
	
	@Transactional(readOnly=true)
	public List<Book> 모두가져오기() {
		return bookRepository.findAll();
	}
	
	@Transactional
	public Book 수정하기(Long id, Book book) {
		Book bookEntity = bookRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!!")); // 영속화
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		return bookEntity;
	} // 함수종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 디비로 갱신(flush) => commit ===> 더티체킹 

	@Transactional
	public String 삭제하기(Long id) {
		bookRepository.deleteById(id);
		return "ok";
	}
}
