package com.example.book.category.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CategoryDto {
	
	private Long id;
	
//	@NotBlank
	private String categoryName;
	
	private String writer;
	
	private String writerCode;
	
	private String note;
	
	private Timestamp insertDT;
	
	private List<CategoryDetailDto> categoryDetailDto;
}
