package com.example.book.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// 단위테스트 - DB 관련된 Bean이 IoC에 등록되면 됨
// @AutoConfigureTestDatabase : DB를 실제DB로 사용할 것인지 가짜(다른)DB로 사용할 것인지 설정할 수 있는 애노테이션
// @AutoConfigureTestDatabase(replace = Replace.ANY)  : 가짜(내장)DB 사용
// @AutoConfigureTestDatabase(replace = Replace.NONE) : 실제DB로 테스트(단위테스트에서는 필요없다) 
// @DataJpaTest // Jpa 관련된 애들만 메모리에 띄운다 - Repository들을 다 IoC에 등록해둠  
@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest
public class BookRepositoryUnitTest {

	// @Mock를 사용할 필요없다 - 위에서 사용한 @DataJpaTest로 인해 이미 떠있기 때문에
	@Autowired
	private BookRepository bookRepository;

	// 크게 중요하거나 비중이 있지 않아 대략적인 틀만 설명하고 넘어감
	@Test
	public void 저장하기_테스트() {
		// BDDMocikto 방식
		// given
		Book book = new Book(null, "책제목1", "책저자1");

		// stub
		// when(bookRepository.save(book)).thenReturn(book);

		// test execute
		Book bookEntity = bookRepository.save(book);

		// then
		assertEquals("책제목1", bookEntity.getTitle());
	}
}
