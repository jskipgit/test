package com.ironyard.repo;

import com.ironyard.data.IronUser;
import com.ironyard.data.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by jasonskipper on 10/31/16.
 */
public interface MovieRepository extends PagingAndSortingRepository<Movie, Long>{

    @Query("select avg(minutes) from Movie m where m.rating = ?1")
    long getAverageMovieLengthByRating(String rating);
}
