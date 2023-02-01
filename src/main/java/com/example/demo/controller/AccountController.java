package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")

public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepository;
	
	//Get all Accounts
		@GetMapping("/accounts")
		public List<Account> listarCuentas(){
			return accountService.getAll();
		}
		
		@PostMapping("/create")
		public Account crateAccount(@RequestBody Account account) {
			return accountService.createAccount(account);
		//return accountRepository.save(account);
		}
		
		@GetMapping("/accounts/{id}")
		public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
			return accountService.findByYd(id);
		}
		
		@PutMapping("/accounts/{id}")
		public ResponseEntity<Account> updateAccount(@PathVariable Long id,
												@RequestBody Account accountDetails){
			return accountService.updateAccount(id, accountDetails);
		}
		
		@DeleteMapping("/accounts/{id}")
	    public ResponseEntity < Map < String, Boolean >> deleteAccount(@PathVariable Long id) {
			
			Account account = accountRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException
							("La cuenta no existe con el id: " + id));
	        accountRepository.delete(account);
	        Map < String, Boolean > response = new HashMap < > ();
	        response.put("deleted", Boolean.TRUE);
	        return ResponseEntity.ok(response);
	    }
		
		@GetMapping("/accounttype")
		public ResponseEntity <List <Account>> getByTypeId(@RequestParam(value = "typeId") final int typeId ){
			return ResponseEntity.ok(accountService.getByTypeId(typeId));
		}
		
		@GetMapping("/statetype")
		public ResponseEntity <List <Account>> getByStateId(@RequestParam(value = "stateId") final int stateId ){
			return ResponseEntity.ok(accountService.getByStateId(stateId));
		}
		
		@GetMapping("/search")
		public ResponseEntity <List <Account>> search(@RequestParam(required = false, name = "typeId") final String typeId , 
														@RequestParam(required = false, name = "stateId") final String stateId){
			List <Account> response = accountService.searchAccounts(typeId, stateId);
			return ResponseEntity.ok(response);
		}
	
}
