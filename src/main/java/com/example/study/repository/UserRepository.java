package com.example.study.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.study.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	//findBy[필드명] : QueryMethod, 쿼리문을 메소드 형태로 작성하는 것
	//select * from where account = ?
	Optional<User> findByAccount(String account);
	
	Optional<User> findByEmail(String email);
	
	//select * from where account = ? and email = ?
	//파라미터는 순서대로 매칭됨
	Optional<User> findByAccountAndEmail(String account, String email);
	
	//id를 역순으로 검색하여 가장 마지막에 등록된 핸드폰번호 하나를 가져옴
	User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}
