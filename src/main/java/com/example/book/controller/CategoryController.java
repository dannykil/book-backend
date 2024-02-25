package com.example.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.Category;
import com.example.book.service.CategoryService;

import lombok.RequiredArgsConstructor;

// @CrossOrigin 여기에 @CrossOrigin를 사용하게 되면 전체에 다 적용된다  
@RequiredArgsConstructor
@RestController
//@CrossOrigin(origins = "*", methods = RequestMethod.GET) // 특정 컨트롤러에만 CORS 적용하고 싶을때
public class CategoryController {

	private final CategoryService categoryService;
	
	@CrossOrigin // 요청이 컨트롤러에 진입하기 직전에 실행됨. 임시방편이고 나중에 CORS 필터를 사용해서 시큐리티가 해제하고 들어오게 해야함 
	@PostMapping("/api/category")
	public ResponseEntity<?> save(@RequestBody Category category){	
		System.out.println(category);
		return new ResponseEntity<>(categoryService.저장하기(category), HttpStatus.CREATED);
		// return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin // 외부에서 들어오는 자바스크립트 요청을 허용해준다 
	@GetMapping("/api/category")
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/api/category/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		return new ResponseEntity<>(categoryService.한건가져오기(id), HttpStatus.OK);
	}
	
//	@CrossOrigin
//	@PutMapping("/book/{id}")
////	@PostMapping("/book/{id}")
//	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book){
//		return new ResponseEntity<>(categoryService.수정하기(id, book), HttpStatus.OK);
//	}
//	
//	@CrossOrigin
//	@DeleteMapping("/book/{id}")
//	public ResponseEntity<?> deleteById(@PathVariable Long id){
//		return new ResponseEntity<>(categoryService.삭제하기(id), HttpStatus.OK);
//	}
}
