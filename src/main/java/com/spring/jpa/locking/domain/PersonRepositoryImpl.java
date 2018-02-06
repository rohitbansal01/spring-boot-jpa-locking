package com.spring.jpa.locking.domain;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public class PersonRepositoryImpl implements CustomPersonRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Person findByName(String name) {
		TypedQuery<Person> query = entityManager.createNamedQuery("findByName", Person.class);
		query.setParameter("name", name);
		return DataAccessUtils.singleResult(query.getResultList());
	}

	@Override
	@Transactional(readOnly = false)
	public Person updateName(Person person, String name) {
		entityManager.lock(person, LockModeType.PESSIMISTIC_READ);
		person.setName(name);
		return entityManager.merge(person);
	}

	@Override
	@Transactional(readOnly = false)
	public Person updateCount(Person person) {
		entityManager.lock(person, LockModeType.PESSIMISTIC_READ);
		person.setCount(person.getCount() + 1);
		return entityManager.merge(person);
	}
	
	@Override
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED)
	public Person findAndUpdate(String name) {
		TypedQuery<Person> query = entityManager.createNamedQuery("findByName", Person.class);
		query.setParameter("name", name);
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		Person person = DataAccessUtils.singleResult(query.getResultList());
		if(person != null) {
			person.setCount(person.getCount() + 1);
		} else {
			person = new Person(name, 1);
		}
		return entityManager.merge(person);
	}

}
