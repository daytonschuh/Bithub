package com.example.myapplication.api

import com.example.myapplication.model.Item
import com.example.myapplication.model.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface Service {

    @GET
    fun getItems(@Url url: String): Call<ItemResponse>

    @GET
    fun getFollowers(@Url url: String): Call<List<Item>>
}