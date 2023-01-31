package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TypeAccount;
import com.example.demo.repository.TypeAccountRepository;


@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")

public class TypeAccountController {
	
		@Autowired
		private TypeAccountRepository typeAccountRepository;
	
		@GetMapping("/typeAccounts")
		public List<TypeAccount> listarCuentas(){
			return typeAccountRepository.findAll();
		}
		
		@PostMapping("/typeAccounts")
		public TypeAccount crateAccount(@RequestBody TypeAccount typeAccount) {
			return typeAccountRepository.save(typeAccount);
		}
		
		@GetMapping("/typeAccounts/{id}")
		public ResponseEntity<TypeAccount> getAccountById(@PathVariable Long id) {
			TypeAccount typeAccount = typeAccountRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException
							("La cuenta no existe con el id: " + id));
			return ResponseEntity.ok(typeAccount);
		}
		
		@PutMapping("/typeAccounts/{id}")
		public ResponseEntity<TypeAccount> updateAccount(@PathVariable Long id,
												@RequestBody TypeAccount typeAccountDetails){
			TypeAccount account = typeAccountRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException
							("La cuenta no existe con el id: " + id));
			account.setName(typeAccountDetails.getName());
			
			TypeAccount updateTypeAccount = typeAccountRepository.save(account);
			return ResponseEntity.ok(updateTypeAccount);
		}
	
}
