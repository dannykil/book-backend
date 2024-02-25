package com.example.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Book;
import com.example.book.service.BookService;

import lombok.RequiredArgsConstructor;

// @CrossOrigin 여기에 @CrossOrigin를 사용하게 되면 전체에 다 적용된다  
@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*", methods = RequestMethod.GET) // 특정 컨트롤러에만 CORS 적용하고 싶을때
public class BookController {

	private final BookService bookService;
	
	// Spring Security(프레임워크급 라이브러리) 적용 - CORS	정책을 가지고 있음 
	@CrossOrigin // 요청이 컨트롤러에 진입하기 직전에 실행됨. 임시방편이고 나중에 CORS 필터를 사용해서 시큐리티가 해제하고 들어오게 해야함 
	@PostMapping("/book")
	public ResponseEntity<?> save(@RequestBody Book book){	
		System.out.println(book);
		return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.CREATED);
		// return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping("/login")
//	public String login(HttpSession session, HttpServletRequest request) throws Exception{
//		System.out.println(session);
//		System.out.println(request);
//		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		PrincipalDetails principalDetails = (PrincipalDetails) principal;
//		System.out.println("getEmail : " + principalDetails.getEmail());
//		
//		session.setAttribute("Username", principalDetails.getUsername());
//		session.setAttribute("Brand", principalDetails.getBrand());
//		session.setAttribute("Branch", principalDetails.getBranch());
//		session.setAttribute("PersonName", principalDetails.getPersonName());
//		session.setAttribute("PersonCode", principalDetails.getPersonCode());
//		session.setAttribute("Email", principalDetails.getEmail());
//		return "redirect:/";
//	}
	
	@CrossOrigin 
	@PostMapping("/book/login")
	public ResponseEntity<?> login(@RequestBody Book book){	
		System.out.println(book);
//		return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.CREATED);
		return null;
	}
	
	@CrossOrigin // 외부에서 들어오는 자바스크립트 요청을 허용해준다 
	@GetMapping("/book")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(bookService.모두가져오기(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(bookService.한건가져오기(id), HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/book/{id}")
//	@PostMapping("/book/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book){
		return new ResponseEntity<>(bookService.수정하기(id, book), HttpStatus.OK);
	}
	
	@CrossOrigin
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		return new ResponseEntity<>(bookService.삭제하기(id), HttpStatus.OK);
	}
}
