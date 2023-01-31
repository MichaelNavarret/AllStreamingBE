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
import com.example.demo.model.TypeState;
import com.example.demo.repository.TypeStateRepository;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")

public class TypeStateController {
	@Autowired
	private TypeStateRepository typeStateRepository;

	@GetMapping("/typeState")
	public List<TypeState> listarCuentas(){
		return typeStateRepository.findAll();
	}
	
	@PostMapping("/typeState")
	public TypeState crateState(@RequestBody TypeState typeState) {
		return typeStateRepository.save(typeState);
	}
	
	@GetMapping("/typeState/{id}")
	public ResponseEntity<TypeState> getStateById(@PathVariable Long id) {
		TypeState typeAccount = typeStateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("La cuenta no existe con el id: " + id));
		return ResponseEntity.ok(typeAccount);
	}
	
	@PutMapping("/typeState/{id}")
	public ResponseEntity<TypeState> updateState(@PathVariable Long id,
											@RequestBody TypeState typeStateDetails){
		TypeState typeState = typeStateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException
						("La cuenta no existe con el id: " + id));
		typeState.setName(typeStateDetails.getName());
		
		TypeState updateTypeState = typeStateRepository.save(typeState);
		return ResponseEntity.ok(updateTypeState);
	}

}
