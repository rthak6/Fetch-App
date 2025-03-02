package com.example.fetchapp;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitClient {
    private static final String BASE_URL = "https://fetch-hiring.s3.amazonaws.com/";
    private static Retrofit retrofit = null;

    public static APIService getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(APIService.class);
    }
}

