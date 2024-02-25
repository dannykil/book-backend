package com.example.book.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.book.model.Book;
import com.example.book.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 단위테스트(컨트롤러만 테스트) - Controller, Filter, ControllerAdvice
@Slf4j
@WebMvcTest // Controller, Filter, ControllerAdvice만 메모리에 띄운다 = 전체 실행하는 것보다 가볍다
// @ExtendWith(SpringExtension.class) // 스프링환경에서 테스트하고 싶을 때 붙여야 한다(우리가 실습중인 Junit5에서는 @WebMvcTest에 포함되어있어 생)
public class BookControllerUnitTest {

	@Autowired // @WebMvcTest에 @AutoConfigureMockMvc가 걸려있기 때문에
	private MockMvc mockMvc;

	// Mock메모리와 스프링메모리에 올라가있는 것은 다르다
	@MockBean // IoC 환경에 bean으로 등록됨 = 실제론 동작이 제대로 안되는 가짜서비스
	private BookService bookService;

	// BDDMockito 패턴 given, when, then
//	@Test
//	// public void save_테스트() throws JsonProcessingException {
//	public void save_테스트() throws Exception {
//		// 1) given - 테스트를 하기 위한 준비
//		// ObjectMapper().writeValueAsString(value) - Object를 Json으로 바꾸는 함수
//		// Controller에서 데이터를 받아 프론트로 전달하기 위해서는 Json으로 파싱해서 전달해야한다.
//		Book book = new Book(null, "제목9", "작가9");
//		String content = new ObjectMapper().writeValueAsString(book);
//		log.info(content);
//
//		// 스텁 - 가정법과 같다 = 미리 행동을 지정한다(통합테스트에서는 필요없다 > 실제 서비스가 실행되기 때문에)
//		// 위에를 실행했을 때 아래와 같이 데이터가 나올 것이라고 지정하는 것 = '스텁'이라고 한다.
//		// 스텁의 기본적인 함수 3가지 : given, when, then
//		when(bookService.저장하기(book)).thenReturn(new Book(1L, "제목9", "작가9"));
//
//		// 2) when - 테스트 실행
//		ResultActions resultAction = mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON_UTF8)
//				.content(content).accept(MediaType.APPLICATION_JSON_UTF8)); // 응답은 무엇으로 하겠는가? > Json
//
//		// 3) then - 테스트 실행에 대한 기대값(검증)
//		resultAction.andExpect(status().isCreated()) // Http Status Code 201을 기대한다
//				.andExpect(jsonPath("$.title").value("제목9")) // $ : 전체결과 + 변수명 = $.title의 결과값이 '제목9'이 맞으면 true
//				.andDo(MockMvcResultHandlers.print()); // print()를 사용하면 결과가 영수증처럼 나온다
//		// MockHttpServletRequest(요청)
//		// MockHttpServletResponse(결과)
//	}
//
////	@Test
////	public void save_테스트() {
////		log.info("save_테스트 시작 ====================================== ");
////		Book book = bookService.저장하기(new Book(null, "제목9", "작가9"));
////		System.out.println(book);
////	}
//
//	@Test
//	public void findAll_테스트() throws Exception {
//		// 1) given
//		List<Book> books = new ArrayList<>();
//		books.add(new Book(null, "스프링부트 따라하기", "코스"));
//		books.add(new Book(null, "리액트 따라하기", "코스"));
//		when(bookService.모두가져오기()).thenReturn(books);
//
//		// 2) when
//		ResultActions resultAction = mockMvc.perform(get("/book").contentType(MediaType.APPLICATION_JSON_UTF8));
//
//		// 3) then
//		// import org.hamcrest.Matchers; - 수동으로 import해줌
//		resultAction.andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(2)))
//				.andExpect(jsonPath("$.[0].title").value("스프링부트 따라하기")).andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void findById_테스트() throws Exception {
//		// 1) given
//		Long id = 1L;
//		when(bookService.한건가져오기(id))
//			.thenReturn(new Book(1L, "자바 공부하기", "쌀"));
//
//		// 2) when
//		ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
//			.accept(MediaType.APPLICATION_JSON_UTF8));
//
//		// 3) then
//		resultAction
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.title").value("자바 공부하기"))
//			.andDo(MockMvcResultHandlers.print());
//	}
//
//	@Test
//	public void update_테스트() throws Exception {
//		// 1) given
//		Long id = 1L;
//		Book book = new Book(null, "C++ 따라하기", "작가9");
//		String content = new ObjectMapper().writeValueAsString(book);
//		when(bookService.수정하기(id, book)).thenReturn(new Book(1L, "C++ 따라하기", "작가9"));
//		
//		// 2) when
//		ResultActions resultAction = mockMvc.perform(put("/book/{id}", id)
//			.contentType(MediaType.APPLICATION_JSON_UTF8)
//			.content(content)
//			.accept(MediaType.APPLICATION_JSON_UTF8)); 
//		
//		// 3) then
//		resultAction
//			.andExpect(status().isOk())
//			.andExpect(jsonPath("$.title").value("C++ 따라하기"))
//			.andDo(MockMvcResultHandlers.print());
//		
//	}
//
//	@Test
//	public void delete_테스트() throws Exception {
//		// 1) given
//		Long id = 1L;
//		when(bookService.삭제하기(id)).thenReturn("ok");
//		
//		// 2) when
//		ResultActions resultAction = mockMvc.perform(delete("/book/{id}", id)
//				.accept(MediaType.TEXT_PLAIN_VALUE)); 
//		
//		// 3) then
//		resultAction
//		.andExpect(status().isOk())
//		//.andExpect(jsonPath("$.title").value("C++ 따라하기"))
//		.andDo(MockMvcResultHandlers.print());
//		
//		MvcResult requestResult = resultAction.andReturn();
//		String result = requestResult.getResponse().getContentAsString();
//		
//		assertEquals("ok", result);
//	}
}
