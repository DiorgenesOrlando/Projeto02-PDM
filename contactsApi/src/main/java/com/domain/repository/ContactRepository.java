package com.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
