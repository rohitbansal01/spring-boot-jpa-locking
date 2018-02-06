package com.spring.jpa.locking.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.jpa.locking.domain.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional
	public void updatePersonCount(String name) {
		personRepository.findAndUpdate(name);
	}

}
