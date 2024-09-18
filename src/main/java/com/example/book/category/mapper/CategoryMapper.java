package com.example.book.category.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.example.book.category.dto.CategoryDetailDto;
import com.example.book.category.dto.CategoryDto;
import com.example.book.category.dto.CategoryListDto;
import com.example.book.category.entity.Category;
import com.example.book.category.entity.CategoryDetail;
	
//@Mapper
//@Mapper(
//        componentModel = "spring", // Spring Bean 으로 생성
//        injectionStrategy = InjectionStrategy.CONSTRUCTOR, // 생성자 주입
//        unmappedTargetPolicy = ReportingPolicy.ERROR // 매핑 실패시 컴파일 에러 
//)
//@Component
@Mapper(componentModel = "spring")
public interface CategoryMapper {
//	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
//	default Category dtoToEntity(CategoryDto categoryDto) {
	static Category CategoryDtoToEntity(CategoryDto categoryDto) {
		
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();
        
        category.setCategoryName( categoryDto.getCategoryName() );
        category.setNote( categoryDto.getNote() );
        category.setInsertDT( categoryDto.getInsertDT() );
        
//        System.out.println("category mapper : " + category);
        
        return category;
    }
	
	
	static List<CategoryDetail> CategoryDetailDtoToEntity(String categoryId, List<CategoryDetailDto> categoryDetailDtos) {
		
        if ( categoryDetailDtos == null ) {
            return null;
        }
        
        List<CategoryDetail> categoryDetails = new ArrayList<>();

        for(CategoryDetailDto categoryDetailDto : categoryDetailDtos){ 
        	CategoryDetail categoryDetail = new CategoryDetail();
        	
        	categoryDetail.setCategoryId(categoryId);
        	categoryDetail.setCategoryDetailName(categoryDetailDto.getCategoryDetailName());
        	categoryDetail.setCategoryDetailNote(categoryDetailDto.getCategoryDetailNote());

        	categoryDetails.add(categoryDetail); 
        }
        
//        System.out.println("categoryDetail mapper : " + categoryDetails);
        
        return categoryDetails;
    }

    List<CategoryListDto> entityListToSimpleResponseDtoList(List<Category> category);
    
    
    static CategoryDto EntityToCategoryDto(Category category, List<CategoryDetail> categoryDetails) {
		
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();
        
        categoryDto.setId( category.getId() );
        categoryDto.setCategoryName( category.getCategoryName() );
        categoryDto.setNote( category.getNote() );
        categoryDto.setInsertDT( category.getInsertDT() );
        
        
        List<CategoryDetailDto> categoryDetailDtos = new ArrayList<>();

        for(CategoryDetail categoryDetail : categoryDetails){ 
        	CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
        	
        	categoryDetailDto.setId(categoryDetail.getId());
        	categoryDetailDto.setCategoryId(categoryDetail.getCategoryId());
        	categoryDetailDto.setCategoryDetailName(categoryDetail.getCategoryDetailName());
        	categoryDetailDto.setCategoryDetailNote(categoryDetail.getCategoryDetailNote());
        	categoryDetailDto.setPriority(categoryDetail.getPriority());

        	categoryDetailDtos.add(categoryDetailDto); 
        }
        
        categoryDto.setCategoryDetailDto( categoryDetailDtos );
        
        return categoryDto;
    }
}
