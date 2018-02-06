package com.spring.jpa.locking.domain;

public interface CustomPersonRepository {

	public Person findByName(String name);

	public Person savePerson(Person person);

	public Person updateCount(Person person);

	public Person findAndUpdate(String name);

}
