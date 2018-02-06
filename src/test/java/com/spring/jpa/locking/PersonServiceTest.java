package com.spring.jpa.locking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.jpa.locking.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JPALockingApplication.class)
public class PersonServiceTest {

	@Autowired
	PersonService personService;

	@Test
	public void testPessimisticLocking() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			final Runnable runnable = () -> {
				for (int j = 1; j < 10; j++) {
					final String personName = "Person " + j;
					personService.updatePersonCount(personName);
				}
			};
			final Thread thread = new Thread(runnable);
			thread.start();
			thread.join();
		}
	}

}
