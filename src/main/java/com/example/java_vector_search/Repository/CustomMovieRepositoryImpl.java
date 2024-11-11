package com.example.java_vector_search.Repository;

import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.example.java_vector_search.models.Movies;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import reactor.core.publisher.Flux;

public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    private final MongoDatabase mongoDatabase;

    public CustomMovieRepositoryImpl(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    @Override
    public Flux<Movies> findMoviesByVector(List<Double> embedding) {
        String indexName = "vector_index";
        int numCandidates = 100;
        int limit = 5;

        Bson vectorSearchStage = new Document("$vectorSearch", new Document()
            .append("index", indexName)
            .append("path", "plot_embedding")
            .append("queryVector", embedding)
            .append("numCandidates", numCandidates)
            .append("limit", limit)
        );

        List<Bson> pipeline = List.of(vectorSearchStage);
        return Flux.from(getMoviesCollection().aggregate(pipeline, Movies.class));
    }

    private MongoCollection<Movies> getMoviesCollection() {
        return mongoDatabase.getCollection("embedded_movies", Movies.class);
    }
}
