package com.example.demo.service;

import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.specification.AccountSpecificationBuilder;
import static com.example.demo.util.SpecificationOperation.EQUALS;

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
	

	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}
	
	public ResponseEntity<Account> findByYd(Long id) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("La cuenta no existe con el id: " + id));
		return ResponseEntity.ok(account);
	}
	
	public ResponseEntity<Account> updateAccount(Long id, Account accountDetails) {
		Account account = accountRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("La cuenta no existe con el id: " + id));
		account.setTypeId(accountDetails.getTypeId());
		account.setDateBorn(accountDetails.getDateBorn());
		account.setStateId(accountDetails.getStateId());
		account.setUser(accountDetails.getUser());
		account.setLastRent(accountDetails.getLastRent());
		account.setCountRent(accountDetails.getCountRent());
		account.setPrice(accountDetails.getPrice());
		account.setLoginEmail(accountDetails.getLoginEmail());
		account.setPassword(accountDetails.getPassword());
		
		Account updateAccount = accountRepository.save(account);
		return ResponseEntity.ok(updateAccount);
	}

	public List <Account> getByStateId(final int stateId) {
		List <Account> accountsByStateId = accountRepository.getAllAccountByStateId(stateId);
		return accountsByStateId;
	}


	public List<Account> searchAccounts( final String typeId, final  String stateId) {
		// TODO Auto-generated method stub
		AccountSpecificationBuilder builder = new AccountSpecificationBuilder();
		if(!Strings.isBlank(typeId) && !Strings.isBlank(stateId)) {
			builder.with(false, "typeId", EQUALS , typeId);
			builder.with(false, "stateId", EQUALS , stateId);
		}
		
		if(!Strings.isBlank(typeId) && Strings.isBlank(stateId)) {
			builder.with(true, "typeId", EQUALS , typeId);
		}
		
		if(Strings.isBlank(typeId) && !Strings.isBlank(stateId)) {
			builder.with(true, "stateId", EQUALS , stateId);
		}
		
		final Specification <Account> specs = builder.build();
		
		List <Account> accountList = findAllByCriteria(specs);
		
		return accountList;
	}
	
	private List <Account> findAllByCriteria(final Specification specs){
		List <Account> result = accountRepository.findAll(specs);
		return result;
	}


	


	

	
}
