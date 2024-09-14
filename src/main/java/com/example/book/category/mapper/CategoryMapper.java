package com.example.book.category.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.book.category.dto.CategoryDto;
import com.example.book.category.dto.CategoryListDto;
import com.example.book.category.entity.Category;
	
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
	
	default Category dtoToEntity(CategoryDto categoryDto) {
		
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryName( categoryDto.getCategoryName() );
        category.setNote( categoryDto.getNote() );
        category.setInsertDT( categoryDto.getInsertDT() );
        
        System.out.println("category mapper : " + category);
        
        return category;
    }

    List<CategoryListDto> entityListToSimpleResponseDtoList(List<Category> category);
}
