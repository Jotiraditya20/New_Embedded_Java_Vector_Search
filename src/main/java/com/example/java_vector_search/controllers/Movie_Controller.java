package com.example.java_vector_search.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;  // Import this

import com.example.java_vector_search.Services.MovieServices;
import com.example.java_vector_search.models.Movies;

import reactor.core.publisher.Mono;

@RestController  // Add this annotation
public class Movie_Controller {
    private final MovieServices movieServices;

    @Autowired
    public Movie_Controller(MovieServices movieServices){
        this.movieServices = movieServices;
    }

    @GetMapping("/movies/semantic-search")
    public Mono<List<Movies>> performSemanticSearch(@RequestParam("plotDescription") String plotDescription){
        System.err.println("Plot:" + plotDescription);
        return movieServices.getMoviesSemanticSearch(plotDescription);
    }
    @GetMapping("/")
public String home() {
    return "Welcome to the API!";
}

}
