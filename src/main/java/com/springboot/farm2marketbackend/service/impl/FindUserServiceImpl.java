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
    public String findIdByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhonenumber(phoneNumber);
        return (user != null) ? user.getUid() : null;
    }

    @Override
    public String findPasswordByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhonenumber(phoneNumber);
        return (user != null) ? user.getPassword() : null;
    }
}

