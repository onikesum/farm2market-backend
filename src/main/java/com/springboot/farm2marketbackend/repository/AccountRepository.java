package com.springboot.farm2marketbackend.repository;

import com.springboot.farm2marketbackend.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
}
