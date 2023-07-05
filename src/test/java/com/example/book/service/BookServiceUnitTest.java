package com.example.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.book.domain.Book;
import com.example.book.domain.BookRepository;

//단위테스트 - 서비스와 관련된 애들만 메모리에 띄우면 됨 
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

	@InjectMocks // BookService 객체가 만들어질 때 BookServiceUnitTest 파일에 @Mock이 등록된 모든 애들을 주입받는다.
	private BookService bookService;

	@Mock
	private BookRepository bookRepository;

	// 크게 중요하거나 비중이 있지 않아 대략적인 틀만 설명하고 넘어감 
	@Test
	public void 저장하기_테스트() {
		// BDDMocikto 방식
		// given
		Book book = new Book();
		book.setTitle("책제목1");
		book.setAuthor("책저자1");

		// stub
		when(bookRepository.save(book)).thenReturn(book);

		// test execute
		Book bookEntity = bookService.저장하기(book);

		// then
		assertEquals(bookEntity, book);
	}
}
