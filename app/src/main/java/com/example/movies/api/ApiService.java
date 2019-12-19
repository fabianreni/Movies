package com.example.movies.api;

import com.example.movies.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/{sort_critical}")
    Call <MovieResponse> getAllMovies (
            @Path("sort_critical") String sort_critical,
            @Query("apy_key") String apy_key,
            @Query("lenguage") String lenguage,
            @Query("page") int page
    );

}

