package com.example.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> { // <T> : 제네릭을 걸어준다. : 무슨말이지? >>> 데이터 타입이 정해져있지 않다라고 이해함

	//HttpStatus status;
	int status;
	T data;
}
