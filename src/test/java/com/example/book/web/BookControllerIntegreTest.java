package com.example.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.domain.Book;
import com.example.book.domain.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional // 각각의 테스트 함수가 종료될 때마다 트랜잭션을 rollback해주는 애노테이션
@AutoConfigureMockMvc // MockMvc를 IoC에 등록해줌
// 통합테스트(컨트롤러로 전체 테스트) = 모든 Bean들을 똑같이 IoC	에 올리고 테스트하는 것
// WebEnvironment.MOCK : 실제 톰캣을 올리는게 아니라 다른 톰캣으로 테스트
// WebEnvironment.RANDOM_PORT : 실제 톰캣으로 테스트  
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class BookControllerIntegreTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	// 각각의 모든 테스트 함수가 실행되기 직전에 실행됨  
	// @BeforeEach와 @AfterEach를 선언하여 자동화 할 수 있다. 하지만 실수할 수 있으니 필요할 때마다 선언해서 사용하겠다. 
	@BeforeEach
	public void init() {
//		List<Book> books = new ArrayList<>(); 
//		books.add(new Book(null, "스프링부트 따라하기", "코스"));
//		books.add(new Book(null, "리액트 따라하기", "코스"));
//		books.add(new Book(null, "Junit 따라하기", "코스"));
//		bookRepository.saveAll(books);
		
		//2023.07.07 수
		//entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate();
		entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id START WITH 1").executeUpdate();
	}
	
//	@AfterEach
//	public void end() {
//		bookRepository.deleteAll();
//	}

	// BookControllerUnitTest에서 그대로 가져옴
	@Test
	// public void save_테스트() throws JsonProcessingException {
	public void save_테스트() throws Exception {
		// 1) given - 테스트를 하기 위한 준비
		Book book = new Book(null, "제목9", "작가9");
		String content = new ObjectMapper().writeValueAsString(book);
		// when(bookService.저장하기(book)).thenReturn(new Book(1L, "제목9", "작가9")); // 실제
		// 서비스가 실행되기 때문에 필요없다
		// @Transactional이 걸려있어 어짜피 데이터가 rollback되지만 지우면 실제로 데이터가 Insert 된다

		// 2) when - 테스트 실행
		ResultActions resultAction = mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content).accept(MediaType.APPLICATION_JSON_UTF8));

		// 3) then - 테스트 실행에 대한 기대값(검증)
		resultAction.andExpect(status().isCreated()).andExpect(jsonPath("$.title").value("제목9"))
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void findAll_테스트() throws Exception {
		// 1) given
		List<Book> books = new ArrayList<>();
		// id값을 null로 주게되면 단위테스트에서는 이상없으나 통합테스트에서는 에러발생 
		books.add(new Book(null, "스프링부트 따라하기", "코스"));
		books.add(new Book(null, "리액트 따라하기", "코스"));
		books.add(new Book(null, "Junit 따라하기", "코스"));
		// when(bookService.모두가져오기()).thenReturn(books);
		bookRepository.saveAll(books);

		// 2) when
		ResultActions resultAction = mockMvc.perform(get("/book").contentType(MediaType.APPLICATION_JSON_UTF8));

		// 3) then
		// import org.hamcrest.Matchers; - 수동으로 import해줌
		resultAction.andExpect(status().isOk())
//				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$.[0].id").value(1L))
				.andExpect(jsonPath("$.[2].title").value("Junit 따라하기"))
				.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception {
		// 1) given
		Long id = 2L;
		List<Book> books = new ArrayList<>(); 
		books.add(new Book(null, "스프링부트 따라하기", "코스"));
		books.add(new Book(null, "리액트 따라하기", "코스"));
		books.add(new Book(null, "Junit 따라하기", "코스"));
		bookRepository.saveAll(books);

		// 2) when
		ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
			.accept(MediaType.APPLICATION_JSON_UTF8));

		// 3) then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("리액트 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void update_테스트() throws Exception {
		// 1) given
		Long id = 3L;
		List<Book> books = new ArrayList<>(); 
		books.add(new Book(null, "스프링부트 따라하기", "코스"));
		books.add(new Book(null, "리액트 따라하기", "코스"));
		books.add(new Book(null, "Junit 따라하기", "코스"));
		bookRepository.saveAll(books);
		
		Book book = new Book(null, "C++ 따라하기", "작가9");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// 2) when
		ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.content(content)
			.accept(MediaType.APPLICATION_JSON_UTF8)); 
		
		// 3) then
		resultAction
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(3L))
			.andExpect(jsonPath("$.title").value("C++ 따라하기"))
			.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	public void delete_테스트() throws Exception {
		// 1) given
		Long id = 1L;
		List<Book> books = new ArrayList<>(); 
		books.add(new Book(null, "스프링부트 따라하기", "코스"));
		books.add(new Book(null, "리액트 따라하기", "코스"));
		books.add(new Book(null, "Junit 따라하기", "코스"));
		bookRepository.deleteById(id);
		String content = new ObjectMapper().writeValueAsString(books);
		
		// 2) when
		ResultActions resultAction = mockMvc.perform(delete("/book/{id}", id)
				.accept(MediaType.TEXT_PLAIN_VALUE)); 
		
		// 3) then
		resultAction
		.andExpect(status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		MvcResult requestResult = resultAction.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
	}
}
