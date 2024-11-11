package com.example.java_vector_search.Repository;

import java.util.List;

import com.example.java_vector_search.models.Movies;

import reactor.core.publisher.Flux;

public interface CustomMovieRepository {
    Flux<Movies> findMoviesByVector(List<Double> embedding);
}