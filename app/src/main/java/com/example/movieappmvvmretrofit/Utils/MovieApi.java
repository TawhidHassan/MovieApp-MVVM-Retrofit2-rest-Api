package com.example.movieappmvvmretrofit.Utils;

import com.example.movieappmvvmretrofit.models.MovieModel;
import com.example.movieappmvvmretrofit.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //search movie
    /**https://api.themoviedb.org/3/search/movie?api_key=dd1c3c69346180699bdb9d6aa48ff8a7&query=jack+Reacher**/
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") String page
    );

    //search movie by id
    /**https://api.themoviedb.org/3/movie/550?api_key=dd1c3c69346180699bdb9d6aa48ff8a7**/
    // remember that movie_id=550 is for jack Rose
    @GET("3/movie/{movie_id}?")
    Call<MovieModel> searchMoviebyId(
            @Path("movie_id") int movie_id,
            @Query("api_key") String key
    );

}
