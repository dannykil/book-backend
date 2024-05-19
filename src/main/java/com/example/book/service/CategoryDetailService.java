package com.example.book.service;

  import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.model.CategoryDetail;
import com.example.book.repository.CategoryDetailRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 기능을 정의할 수 있고, 트랜잭션을 관리 수 있다 
public class CategoryDetailService {
	
	private final CategoryDetailRepository categoryDetailRepository;
	
	
	@Transactional // 서비스 함수가 종료될 때 commit할지 rollback할지 트랜잭션 관리하겠다.
	public CategoryDetail 저장하기(CategoryDetail categoryDetail) {
		return categoryDetailRepository.save(categoryDetail);
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDetail> findAll() {
		return categoryDetailRepository.findAll();
	}
}
