package com.example.myapplication.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("login")
    @Expose
    private lateinit var login: String

    @SerializedName("avatar_url")
    @Expose
    private lateinit var avatarUrl: String

    @SerializedName("html_url")
    @Expose
    private lateinit var htmlUrl: String

    //@SerializedName("followers_url")
    //@Expose
    //private lateinit var followersUrl: String

    fun Item(login: String, avatarUrl: String, htmlUrl: String/*, followersUrl: String*/) {
        this.login = login
        this.avatarUrl = avatarUrl
        this.htmlUrl = htmlUrl
        //this.followersUrl = followersUrl
    }

    fun getLogin(): String {
        return login
    }

    fun setLogin(login: String) {
        this.login = login
    }

    fun getAvatarUrl(): String {
        return avatarUrl
    }

    fun setAvatarUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

    fun getHtmlUrl(): String {
        return htmlUrl
    }

    fun setHtmlUrl(htmlUrl: String) {
        this.htmlUrl = htmlUrl
    }

    //fun getFollowers(login: String): Int{

    //    return
    //}
}