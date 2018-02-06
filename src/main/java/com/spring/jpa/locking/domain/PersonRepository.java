package com.spring.jpa.locking.domain;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Serializable>, CustomPersonRepository {

}
