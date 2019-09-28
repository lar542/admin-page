package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;

public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
    	String account = "Test03";
    	String password = "Test03";
    	String status = "REGISTERED";
    	String email = "Test01@gmail.com";
    	String phoneNumber = "010-1234-3333";
    	
    	User user = User.builder()
    			.account(account)
    			.password(password)
//    			.status(status)
    			.email(email)
    			.phoneNumber(phoneNumber)
    			.build();
    	
    	User newUser = userRepository.save(user);
    	Assert.assertNotNull(newUser);
    }

//    @Test
//    @Transactional
    public void read(){
        User findUser = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1234-4321");
        
        findUser.getOrderGroup().stream().forEach(orderGroup -> {
        	System.out.println("수령인 : " + orderGroup.getRevName());
        	System.out.println("수령지" + orderGroup.getRevAddress());
        	
        	orderGroup.getOrderDetails().forEach(orderDetail -> {
        		System.out.println("파트너사이름 : " + orderDetail.getItem().getPartner().getName());
        		System.out.println("파트너사카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
        		System.out.println("주문상품 : " + orderDetail.getItem().getName());
        		System.out.println("고객센터번호 : " + orderDetail.getItem().getPartner().getCallCenter());
        		System.out.println("주문상태 : " + orderDetail.getStatus());
        		System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());
        		
        	});
        });
    }

//    @Test
    public void update(){
        Optional<User> findUser = userRepository.findById(2L);

        findUser.ifPresent(selectUser -> {
            selectUser.setAccount("ppppp");
//            selectUser.setUpdatedAt(LocalDateTime.now());
//            selectUser.setUpdatedBy("update method()");
            
            userRepository.save(selectUser);
        });
    }

//    @Test
    public void delete(){
        Optional<User> findUser = userRepository.findById(1L);

        Assert.assertTrue(findUser.isPresent()); //true여야만 실행됨

        findUser.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent()); //false
    }
}
