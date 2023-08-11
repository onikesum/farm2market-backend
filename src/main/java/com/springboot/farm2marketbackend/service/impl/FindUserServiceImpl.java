package com.springboot.farm2marketbackend.service.impl;

import com.springboot.farm2marketbackend.data.entity.User;
import com.springboot.farm2marketbackend.repository.UserRepository;
import com.springboot.farm2marketbackend.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// FindUserServiceImpl.java
@Service
public class FindUserServiceImpl implements FindUserService {

    private final UserRepository userRepository;

    @Autowired
    public FindUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String findIdByPhoneNumberAndName(String phoneNumber, String name) {
        User user = userRepository.findByPhonenumberAndName(phoneNumber, name);
        return (user != null) ? user.getUid() : null;
    }

    @Override
    public String findPasswordByPhoneNumberAndNameAndUid(String phoneNumber, String name, String uid) {
        User user = userRepository.findByPhonenumberAndNameAndUid(phoneNumber, name, uid);
        return (user != null) ? user.getPassword() : null;
    }
}

