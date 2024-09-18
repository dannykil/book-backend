package com.example.book.category.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.category.entity.CategoryDetail;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long>{ 

	List<CategoryDetail> findAllByCategoryId(String categoryId);
}
