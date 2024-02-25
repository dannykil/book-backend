package com.example.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{ 

}
