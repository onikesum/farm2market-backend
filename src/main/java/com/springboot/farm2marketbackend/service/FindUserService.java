package com.springboot.farm2marketbackend.service;
public interface FindUserService {
    String findIdByPhoneNumber(String phoneNumber);
    String findPasswordByPhoneNumber(String phoneNumber);
}

