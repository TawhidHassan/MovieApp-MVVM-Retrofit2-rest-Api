package com.example.movieappmvvmretrofit.request;

import com.example.movieappmvvmretrofit.Utils.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.movieappmvvmretrofit.Utils.Credentials.BASE_URL;

public class Servicey {

    public static Retrofit.Builder retrofitBuilder=
            new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit=retrofitBuilder.build();


    private static MovieApi movieApi=retrofit.create(MovieApi.class);
    public MovieApi getMovieApi(){
        return movieApi;
    }
}
