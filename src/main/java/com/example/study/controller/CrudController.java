package com.example.study.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.service.BaseService;

/**
 * Controller의 반복되는 코드를 추상화하기 위한 추상 클래스
 * Req, Res를 받아서 CrudInterface로 전달한다. 
 *
 * @param <Req>
 * @param <Res>
 */
@Component //@Autowired로 주입받기 위함
public abstract class CrudController<Req, Res, Entity> implements CrudInterface<Req, Res> {

	//Controller에 Service를 연결시키는 부분
	//Entity 타입에 해당되는 Service를 찾아 주입
	@Autowired(required = false) //있을 수도 없을 수도 있음
	protected BaseService<Req, Res, Entity> baseService;

	@Override
	@PostMapping("")
	public Header<Res> create(@RequestBody Header<Req> request){
		return baseService.create(request);
	}
	
	@Override
	@GetMapping("{id}")
	public Header<Res> read(@PathVariable Long id){
		return baseService.read(id);
	}
	
	@Override
	@PutMapping("")
	public Header<Res> update(@RequestBody Header<Req> request){
		return baseService.update(request);
	}
	
	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id){
		return baseService.delete(id);
	}
	
	@Override
	@GetMapping("")
	public Header<List<Res>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 15) Pageable pageable) {
		return baseService.search(pageable);
	}
}
