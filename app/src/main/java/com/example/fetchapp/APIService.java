package com.example.fetchapp;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface APIService {
    @GET("hiring.json")
    Call<List<Item>> getItems();
}