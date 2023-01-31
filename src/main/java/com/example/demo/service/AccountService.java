package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	public List <Account> getByTypeId(final int typeId) {
		List <Account> accountsByTypeId = accountRepository.getAllAccountByTypeId(typeId);
		return accountsByTypeId;
	}


	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}


	public List <Account> getByStateId(int stateId) {
		List <Account> accountsByStateId = accountRepository.getAllAccountByStateId(stateId);
		return accountsByStateId;
	}

	
}
