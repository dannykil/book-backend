package com.example.book.category.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryListDto {
	
	private Long id;
	
	private String categoryName;
	
	private String writer;
	
	private String writerCode;
	
	private String note;
	
	private Timestamp insertDT;
	
	public CategoryListDto(CategoryDto categoryDto) {
        this.id = categoryDto.getId();
        this.categoryName = categoryDto.getCategoryName();
        this.writer = categoryDto.getWriter();
        this.writerCode = categoryDto.getWriterCode();
        this.note = categoryDto.getNote();
        this.insertDT = categoryDto.getInsertDT();
    }
	
	public static CategoryListDto of(CategoryDto categoryDto) {
        return new CategoryListDto(categoryDto);
    }
}
