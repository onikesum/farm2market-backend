package com.springboot.farm2marketbackend.service;
public interface FindUserService {
    String findIdByPhoneNumberAndName(String phoneNumber, String name);
    String findPasswordByPhoneNumberAndNameAndUid(String phoneNumber, String name, String uid);
}

