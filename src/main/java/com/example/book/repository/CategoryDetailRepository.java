package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.CategoryDetail;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long>{ 

}
