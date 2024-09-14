package com.example.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.ResponseDto;
import com.example.book.model.User;
import com.example.book.service.UserService;

@RestController 
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("/api/user/insert")
//	public ResponseDto<Integer> insert(HttpSession session, @RequestBody User user) {
//		System.out.println(user);
//		userService.insertUser(session, user);
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
//	@PostMapping("/api/user/insert")
//	public ResponseDto<Integer> insert(@RequestBody User user) {
//		System.out.println(user);
//		userService.insertUser(user);
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
	
//	@PutMapping("/api/employee/update/{id}")
//	public ResponseDto<Integer> update(@PathVariable int id, @RequestBody User user) {
//		System.out.println(user);
//		userService.updateEmployee(id, user);
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
//	@PutMapping("/api/employee/delete/{id}")
//	public ResponseDto<Integer> delete(HttpSession session, @PathVariable int id) {
//		userService.deleteEmployee(session, id);
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
