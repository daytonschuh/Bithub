package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import org.json.JSONObject

class FollowersResponse {

    //@SerializedName("body")
    //@Expose

    private lateinit var body: List<Item>

    fun getFollowers(): List<Item> {
        return body
    }

}