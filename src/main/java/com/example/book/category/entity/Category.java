package com.example.book.category.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String categoryName;
	private String writer;
	private String writerCode;
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp insertDT;
	private String note;
//	private CategoryDetail categoryDetail;
}
