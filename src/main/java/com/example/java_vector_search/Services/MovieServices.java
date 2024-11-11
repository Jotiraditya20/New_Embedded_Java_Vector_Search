package com.example.java_vector_search.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.java_vector_search.Repository.Movie_Repository;
import com.example.java_vector_search.models.Movies;

import reactor.core.publisher.Mono;

@Service // Mark the class as a Spring service
public class MovieServices {
    private final Movie_Repository movieRepository; // Consistent naming convention
    private final OpenAIService embedder;

    @Autowired
    public MovieServices(Movie_Repository movieRepository, OpenAIService embedder) {
        this.movieRepository = movieRepository;
        this.embedder = embedder;
    }

    public Mono<List<Movies>> getMoviesSemanticSearch(String plotDescription) {
        return embedder.createEmbeddings(plotDescription) // Generate embeddings
            .flatMapMany(embedding -> movieRepository.findMoviesByVector(embedding)) // Search movies
            .collectList(); // Collect results into a list
    }
}
