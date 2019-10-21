package com.example.study.model.network;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonInclude(value = Include.CUSTOM) //어떤 값들만 include 할 건지 설정할 수 있음 
public class Header<T> {

	//api 통신 시간
	private LocalDateTime transactionTime;
	
	//api 응답 코드
	private String resultCode;
	
	//api 부가 설명
	private String description;
	
	//json body
	private T data;
	
	// pagination
    private Pagination pagination;
	
	/**
	 * 정상적인 통신
	 * @param <T>
	 * @return
	 */
	public static <T> Header<T> OK(){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.build();
	}
	
	/**
	 * 데이터를 받는 OK
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static <T> Header<T> OK(T data){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("OK")
				.description("OK")
				.data(data)
				.build();
	}
	
	/**
	 * 페이징을 포함한 통신
	 * @param <T>
	 * @param data
	 * @param pagination
	 * @return
	 */
	public static <T> Header<T> OK(T data, Pagination pagination){
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }
	
	/**
	 * 설명을 가지고 있고 데이터가 없는 에러
	 * @param <T>
	 * @param description
	 * @return
	 */
	public static <T> Header<T> ERROR(String description){
		return (Header<T>) Header.builder()
				.transactionTime(LocalDateTime.now())
				.resultCode("ERROR")
				.description(description)
				.build();
	}
}
