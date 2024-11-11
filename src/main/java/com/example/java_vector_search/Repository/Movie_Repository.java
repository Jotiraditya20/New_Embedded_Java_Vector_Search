package com.example.java_vector_search.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.java_vector_search.models.Movies;

@Repository
public interface Movie_Repository extends ReactiveMongoRepository<Movies, String>, CustomMovieRepository {
    // You can add more query methods if needed
}
