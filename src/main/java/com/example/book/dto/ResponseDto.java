package com.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//@NoArgsConstructor
//@Builder
//public class ResponseDto<T> { // <T> : 제네릭을 걸어준다. : 무슨말이지? >>> 데이터 타입이 정해져있지 않다라고 이해함
//
//	//HttpStatus status;
//	int status;
//	T data;
//}

@Data
@AllArgsConstructor
public class ResponseDto { 
	private Object data;

    public static ResponseDto of(Object data) {
//    	System.out.println("ResponseDto data : " + data);
    	
        return new ResponseDto(data);
    }
}
