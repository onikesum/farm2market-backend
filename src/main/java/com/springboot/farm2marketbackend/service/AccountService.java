package com.springboot.farm2marketbackend.service;

import com.springboot.farm2marketbackend.data.dto.AccountForm;
import com.springboot.farm2marketbackend.data.entity.Account;
import com.springboot.farm2marketbackend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountForm form) {
        Account account = form.toEntity();
        accountRepository.save(account);
        return account.getId();
    }
}
