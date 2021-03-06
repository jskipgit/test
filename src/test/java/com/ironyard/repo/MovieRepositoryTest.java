package com.ironyard.repo;

import com.ironyard.data.Movie;
import com.ironyard.dto.BollyWoodMovie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;

/**
 * Created by jasonskipper on 11/2/16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MovieRepositoryTest {
    @Before
    public void setUp() throws Exception {
        Iterable<Movie> movies = movieRepo.findAll();
        for(Movie m:movies){
            movieRepo.delete(m);
        }

    }

    @Autowired
    private MovieRepository movieRepo;

    @Test
    public void getAverageMovieLengthByRating() throws Exception {
        // create movie
        Movie savedMovie = movieRepo.save(new Movie("Matrix", "R", "http://url", "Awesome Movie", 100));

        Movie savedMovie2 = movieRepo.save(new Movie("Matrix", "G", "http://url", "Awesome Movie", 500));

        Movie savedMovie3 = movieRepo.save(new Movie("Matrix", "R", "http://url", "Awesome Movie", 200));

        long ave = movieRepo.getAverageMovieLengthByRating("R");
        assertEquals(150, ave);

    }


}