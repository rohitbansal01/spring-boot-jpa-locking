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
	public Person savePerson(Person person) {
		return entityManager.merge(person);
	}

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public Person updateCount(Person inputPerson) {
		Person person = null;
		if (inputPerson != null) {
			person = entityManager.find(Person.class, inputPerson.getId(), LockModeType.PESSIMISTIC_WRITE);
			person.setCount(person.getCount() + 1);
			return entityManager.merge(person);
		} else {
			throw new RuntimeException("Person is null");
		}
	}

	/*
	 * Below method works fine in MS SQL Server. But getting following warning
	 * message generated with Oracle Dialect:
	 * 
	 * "Encountered request for locking however dialect reports that database prefers locking be done 
	 * in a separate select (follow-on locking); results will be locked after initial query executes"
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.spring.jpa.locking.domain.CustomPersonRepository#findAndUpdate(java.lang.
	 * String)
	 */

	@Override
	@Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
	public Person findAndUpdate(String name) {
		TypedQuery<Person> query = entityManager.createNamedQuery("findByName", Person.class);
		query.setParameter("name", name);
		query.setLockMode(LockModeType.PESSIMISTIC_WRITE);
		Person person = DataAccessUtils.singleResult(query.getResultList());
		if (person != null) {
			person.setCount(person.getCount() + 1);
		} else {
			person = new Person(name, 1);
		}
		return entityManager.merge(person);
	}

}
