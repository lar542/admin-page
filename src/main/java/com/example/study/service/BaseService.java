package com.example.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.response.UserOrderInfoApiResponse;

@Component //@Autowired로 주입받기 위함
public abstract class BaseService<Req, Res, Entity> implements CrudInterface<Req, Res> {
	
	//Service에 Repository를 연결시키는 부분
	//JpaRepository 중에 해당 엔티티와 타입을 가진 Repository를 찾아 주입
	@Autowired(required = false) //있을 수도 없을 수도 있음
	protected JpaRepository<Entity, Long> baseRepository;

	public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
		return null;
	}

}