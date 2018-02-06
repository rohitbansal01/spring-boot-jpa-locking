package com.spring.jpa.locking;

import org.hibernate.StaleObjectStateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.jpa.locking.domain.Movie;
import com.spring.jpa.locking.service.MovieService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JPALockingApplication.class)
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@Test
	public void testOptimisticLocking() throws InterruptedException {
		for (int i = 0; i < 50; i++) {
			final Runnable runnable = () -> {
				for (int j = 1; j < 10; j++) {
					final String movieName = "Movie " + j;
					final Movie movie = new Movie(movieName, 1);
					boolean isMovieAdded = true;
					do {
						try {
							movieService.addMovie(movie);
							isMovieAdded = true;
						} catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
							isMovieAdded = false;
						}
					} while (!isMovieAdded);
				}
			};
			final Thread thread = new Thread(runnable);
			thread.start();
			thread.join();
		}
	}

}
