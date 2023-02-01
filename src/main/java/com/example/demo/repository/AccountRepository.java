package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor {
	List<Account> getAllAccountByTypeId(Integer typeId);
	
	List<Account> getAllAccountByStateId(Integer stateId);
}
