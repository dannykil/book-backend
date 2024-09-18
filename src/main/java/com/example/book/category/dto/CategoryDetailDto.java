package com.example.book.category.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CategoryDetailDto {
	
//	private Long categoryDetailNameId;
//	
//	private String priority;
//	
//	private String categoryDetailName;
//	
//	private String categoryDetailNote;
	
	
//	private Long id;
//	
//	private String categoryId;
//	
//	private String categoryName;
//	
//	private String note;
	
	
	private Long id;
	private String categoryId;
	private String writer;
	private String writerCode;
//	private String categoryName;
//	private String note;
	private String categoryDetailName;
	private String categoryDetailNote;
	private int priority;
	@CreationTimestamp // 시간이 자동으로 입력된다.
	private Timestamp insertDT;
}
