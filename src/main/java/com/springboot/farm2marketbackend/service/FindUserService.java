package com.springboot.farm2marketbackend.service;
public interface FindUserService {
    String findIdByPhoneNumberAndName(String phoneNumber, String name);
    String findPasswordByPhoneNumberAndName(String phoneNumber, String name);
}

