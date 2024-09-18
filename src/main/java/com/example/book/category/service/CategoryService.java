package com.example.book.category.service;

  import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.category.entity.Category;
import com.example.book.category.entity.CategoryDetail;
import com.example.book.category.repository.CategoryDetailRepository;
import com.example.book.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 기능을 정의할 수 있고, 트랜잭션을 관리 수 있다 
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryDetailRepository categoryDetailRepository;
	
	@Transactional(readOnly=true)
	public List<Category> findAll() {
		
		List<Category> category = categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
        if (category.isEmpty()) {
            return new ArrayList<>();
        }
        
//        System.out.println("category service : " + category);
        
        return category;
	}
	
	@Transactional // 서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션 관리하겠다.
	public Category writeCategory(Category category) {

		return categoryRepository.save(category);
	}
	
	@Transactional 
	public List<CategoryDetail> writeCategoryDetail(List<CategoryDetail> categoryDetail) {
		System.out.println("categoryDetailResult : " + categoryDetail);
        
		return categoryDetailRepository.saveAll(categoryDetail);
	}
	
//	@Transactional(readOnly=true) // JPA 변경감지라는 내부기능이 활성화 안됨. update 시 정합성 유지해줌.insert의 유령데이터현상(팬텀현상)은 못막음.
//	public Category findById(Long id) {
//		
//		return categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!!"));
//	}
//	
//	@Transactional(readOnly=true)
//	public List<CategoryDetail> findAllByCategoryId(String categoryId) {
//		
//		List<CategoryDetail> categoryDetail = categoryDetailRepository.findAllByCategoryId(categoryId);
//		
//        if (categoryDetail.isEmpty()) {
//            return new ArrayList<>();
//        }
//        
//        return categoryDetail;
//	}
	
//	@Transactional(readOnly=true)
//	public List<Category> findAll() {
//		return categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
//	}
	
//	@Transactional // 서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션 관리하겠다.
//	public CategoryDto 저장하기(CategoryDto categoryDto) {
//		return categoryRepository.save(categoryDto);
//	}
	
	
	
//	@Transactional(readOnly=true) // JPA 변경감지라는 내부기능이 활성화 안됨. update 시 정합성 유지해줌.insert의 유령데이터현상(팬텀현상)은 못막음.
//	public Category 한건가져오기(Long id) {
//		return categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!!"));
//	}
//	
//	@Transactional
////	public Category 수정하기(Long id, Category category) {
//	public Category 수정하기(Long id, Category category) {
//		Category categoryEntity = categoryRepository.findById(id).orElseThrow(()->new IllegalArgumentException("id를 확인해주세요!!")); // 영속화
//		categoryEntity.setCategoryName(category.getCategoryName());
//		categoryEntity.setNote(category.getNote());
//		return categoryEntity;
//	} // 함수종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 디비로 갱신(flush) => commit ===> 더티체킹 
//
//	@Transactional
//	public String 삭제하기(Long id) {
//		categoryRepository.deleteById(id);
//		return "ok";
//	}
}
