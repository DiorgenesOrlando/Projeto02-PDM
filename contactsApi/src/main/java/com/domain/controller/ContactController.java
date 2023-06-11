package com.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.model.Contact;
import com.domain.repository.ContactRepository;

@RestController
@RequestMapping(value = "/contacts")

public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@GetMapping
	public List<Contact> listar(){
		return contactRepository.findAll();
		
	}

	@GetMapping("{id}")
	public Optional<Contact> buscar(@PathVariable long id){
		return contactRepository.findById(id);
		
	}

}
