package com.example.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		System.out.println("Category Write : " + category);
//		System.out.println("category.getCategoryName() : " + category.getCategoryName());
//		System.out.println("category.getNote()     : " + category.getNote());
		return new ResponseEntity<>(categoryService.저장하기(category), HttpStatus.CREATED);
		// return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin // 외부에서 들어오는 자바스크립트 요청을 허용해준다 
	@GetMapping("/api/category")
	public ResponseEntity<?> findAll(){
		System.out.println("Category List - webhook test 2");
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/api/category/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		System.out.println("Category Read : " + id);
		return new ResponseEntity<>(categoryService.한건가져오기(id), HttpStatus.OK);
	}
	
	@CrossOrigin
//	@PutMapping("/api/category")
	@PatchMapping("/api/category")
	public ResponseEntity<?> update(@RequestBody Category category){
//	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){
		System.out.println("Category Edit category : " + category);
		System.out.println("Category Edit category.getId() : " + category.getId());
		return new ResponseEntity<>(categoryService.수정하기(category.getId(), category), HttpStatus.OK);
	}
	
	// 2024.04.14 - @DeleteMapping을 사용하기 위해서는 환경설정 내 아래를 추가해야하는데 yml이 아니라 어디에 추가해야할지 모르겠음
	// https://pinokio0702.tistory.com/209 <<< 여기 참
//	@CrossOrigin
//	@DeleteMapping("/api/category/{id}")
//	public ResponseEntity<?> deleteById(@PathVariable Long id){
//		System.out.println("Category Delete : " + id);
//		return new ResponseEntity<>(categoryService.삭제하기(id), HttpStatus.OK);
//	}
	
	@CrossOrigin
	@RequestMapping(value = "/api/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		System.out.println("Category Delete : " + id);
		return new ResponseEntity<>(categoryService.삭제하기(id), HttpStatus.OK);
	}
}
