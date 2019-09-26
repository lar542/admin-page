package com.example.study.ifs;

import com.example.study.model.network.Header;

/**
 * API Controller에서 CRUD 메소드 작성을 강제하기 위함 
 */
public interface CrudInterface<Req, Res> {

	Header<Res> create(Header<Req> request);
	
	Header<Res> read(Long id);
	
	Header<Res> update(Header<Req> request);
	
	Header delete(Long id);
}
