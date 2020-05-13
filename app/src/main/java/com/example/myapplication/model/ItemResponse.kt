package com.example.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ItemResponse {

    @SerializedName("items")
    @Expose

    private lateinit var items: List<Item>

    fun getItems(): List<Item> {
        return items
    }

    fun setItems(items: List<Item>) {
        this.items = items
    }
}