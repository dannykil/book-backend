package com.example.book.category.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.category.dto.CategoryDto;
import com.example.book.category.entity.Category;
import com.example.book.category.entity.CategoryDetail;
import com.example.book.category.mapper.CategoryMapper;
import com.example.book.category.service.CategoryService;
import com.example.book.dto.ResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @CrossOrigin 여기에 @CrossOrigin를 사용하게 되면 전체에 다 적용된다  
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Validated
//@CrossOrigin(origins = "*", methods = RequestMethod.GET) // 특정 컨트롤러에만 CORS 적용하고 싶을때
public class CategoryController {

	private final CategoryService categoryService;
	
	private final CategoryMapper categoryMapper;
	
	@GetMapping("/category")
	public ResponseEntity<?> findAll(){
		
		List<Category> category = categoryService.findAll();
//		System.out.println("category controller list : " + category);
		
		try {
//			System.out.println("OS : " + System.getProperty("os.name").toLowerCase());
//			System.out.println("User ID : " + System.getProperty("user.name").toLowerCase());
//			System.out.println("getHostName() : " + InetAddress.getLocalHost().getHostName());
//			System.out.println("getHostAddress() : " + InetAddress.getLocalHost().getHostAddress());
//			System.out.println("getAddress() : " + InetAddress.getLocalHost().getAddress());
			System.out.println("########## >>>>> " + System.getProperty("user.name").toLowerCase() + "@" + InetAddress.getLocalHost().getHostAddress() + "/category");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        return new ResponseEntity<>(
                ResponseDto.of(categoryMapper.entityListToSimpleResponseDtoList(category)),
                HttpStatus.OK
        );
	}	 
	
	@PostMapping("/category")
	public ResponseEntity<?> writeCategory(@RequestBody CategoryDto categoryDto){	
		System.out.println("category controller write(categoryDto) : " + categoryDto);
		
		Category category = categoryService.writeCategory(CategoryMapper.CategoryDtoToEntity(categoryDto));
		
		List<CategoryDetail> categoryDetail = categoryService.writeCategoryDetail(CategoryMapper.CategoryDetailDtoToEntity(category.getId().toString(), categoryDto.getCategoryDetailDto()));
	
		return new ResponseEntity<>(category, HttpStatus.CREATED);
//        ProfileResponseDto responseDto = ProfileResponseDto.of(saveProfile);
//        return new ResponseEntity<>(
//                PageResponseDto.of(
//                        responseDto,
//                        saveProfile.getReviews()),
//                HttpStatus.CREATED
//        );
	}
	
//	@GetMapping("/category/{categoryId}")
//	public ResponseEntity<?> findById(@PathVariable("categoryId") Long id){
//		
//		Category category = categoryService.findById(id);
//		List<CategoryDetail> categoryDetails =  categoryService.findAllByCategoryId(category.getId().toString());
//		
//		CategoryDto categoryDto = CategoryMapper.EntityToCategoryDto(category, categoryDetails);
//		System.out.println("categoryDto : " + categoryDto);
//		
//		return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//	}
	
	
//	@GetMapping("/category")
//	public ResponseEntity<?> findAll(){
//		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
//	}
	
	
//	@CrossOrigin // 요청이 컨트롤러에 진입하기 직전에 실행됨. 임시방편이고 나중에 CORS 필터를 사용해서 시큐리티가 해제하고 들어오게 해야함 
//	@PostMapping("/api/category")
////	public ResponseEntity<?> save(@RequestBody Category category){
//	public ResponseEntity writeCategory(@RequestBody Category category){	
//		System.out.println("Category Write : " + category);
////		System.out.println("category.getCategoryName() : " + category.getCategoryName());
////		System.out.println("category.getNote()     : " + category.getNote());
//		return new ResponseEntity<>(categoryService.저장하기(category), HttpStatus.CREATED);
////		return new ResponseEntity<>(categoryService.저장하기(category), HttpStatus.CREATED);
//		// return new ResponseEntity<>(bookService.저장하기(book), HttpStatus.BAD_REQUEST);
//	}
	
//	@PostMapping("/{userId}")
//    public ResponseEntity postProfile(
//            @PathVariable @Positive Long userId,
//            @RequestBody @Valid ProfileDto profileDto,
//            @PageableDefault(page = 0, size = 5, sort = "reviewId", direction = Sort.Direction.DESC)
//            Pageable pageable
//    ) {
//        ProfilePageDto saveProfile = profileService.createProfile(
//                userId,
//                profileMapper.dtoToEntity(profileDto),
//                profileDto.getSubjects(),
//                pageable);
//        ProfileResponseDto responseDto = ProfileResponseDto.of(saveProfile);
//        return new ResponseEntity<>(
//                PageResponseDto.of(
//                        responseDto,
//                        saveProfile.getReviews()),
//                HttpStatus.CREATED
//        );
//    }
	
//	@CrossOrigin // 외부에서 들어오는 자바스크립트 요청을 허용해준다 
//	@GetMapping("/api/category")
//	public ResponseEntity<?> findAll(){
//		System.out.println("Category List - webhook test 7");
//		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
//	}
//	
//	@CrossOrigin
//	@GetMapping("/api/category/{id}")
//	public ResponseEntity<?> findById(@PathVariable Long id){
//		System.out.println("Category Read : " + id);
//		return new ResponseEntity<>(categoryService.한건가져오기(id), HttpStatus.OK);
//	}
//	
//	@CrossOrigin
////	@PutMapping("/api/category")
//	@PatchMapping("/api/category")
//	public ResponseEntity<?> update(@RequestBody Category category){
////	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){
//		System.out.println("Category Edit category : " + category);
//		System.out.println("Category Edit category.getId() : " + category.getId());
//		return new ResponseEntity<>(categoryService.수정하기(category.getId(), category), HttpStatus.OK);
//	}
//	
//	// 2024.04.14 - @DeleteMapping을 사용하기 위해서는 환경설정 내 아래를 추가해야하는데 yml이 아니라 어디에 추가해야할지 모르겠음
//	// https://pinokio0702.tistory.com/209 <<< 여기 참
////	@CrossOrigin
////	@DeleteMapping("/api/category/{id}")
////	public ResponseEntity<?> deleteById(@PathVariable Long id){
////		System.out.println("Category Delete : " + id);
////		return new ResponseEntity<>(categoryService.삭제하기(id), HttpStatus.OK);
////	}
//	
//	@CrossOrigin
//	@RequestMapping(value = "/api/category/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<?> deleteById(@PathVariable Long id){
//		System.out.println("Category Delete : " + id);
//		return new ResponseEntity<>(categoryService.삭제하기(id), HttpStatus.OK);
//	}
}
