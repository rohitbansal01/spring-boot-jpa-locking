package com.spring.jpa.locking.domain;

public interface CustomPersonRepository {

	public Person findByName(String name);

	public Person updateName(Person person, String name);

	public Person updateCount(Person person);

	public Person findAndUpdate(String name);

}
