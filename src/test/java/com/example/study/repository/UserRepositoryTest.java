package com.example.study.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;

import junit.framework.Assert;

public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection (DI)
    @Autowired
    private UserRepository userRepository;

//    @Test
    public void create(){
        User user = new User();
        user.setAccount("testuser02");
        user.setEmail("testuser02@gmail.com");
        user.setPhoneNumber("010-1234-1234");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setCreatedBy("testuser02");

        User newUser = userRepository.save(user);
        System.out.println("newUser : " + newUser);
    }

    @Test
    @Transactional
    public void read(){
        Optional<User> findUser = userRepository.findByAccount("ppppp");

        //findUser를 selectUser로 칭하고 있는 경우에 출력
//        findUser.ifPresent(selectUser -> {
//        	selectUser.getOrderDetails().stream().forEach(detail -> {
//        		Item item = detail.getItem();
//        		System.out.println(item);
//        	});
//        });
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
