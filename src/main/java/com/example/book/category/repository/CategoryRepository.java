package com.example.book.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{ 

}

//public interface CategoryRepository extends JpaRepository<Category, Long>{ 
//
//}
