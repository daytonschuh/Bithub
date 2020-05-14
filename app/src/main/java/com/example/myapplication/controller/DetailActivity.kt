package com.example.myapplication.controller

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api.Client
import com.example.myapplication.api.Service
import com.example.myapplication.model.Item
import kotlinx.android.synthetic.main.activity_details_page.*
import kotlinx.android.synthetic.main.user_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    var Link: TextView? = null
    var Username: TextView? = null
    var mActionBarToolbar: Toolbar? = null
    var imageView: ImageView? = null
    var Followers: TextView? = null
    var numberFollowers = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)

        val username = intent.extras!!.getString("login")
        if (username != null) {
            getFollowers(username)
        }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById<View>(R.id.userImageHeader) as ImageView
        Username = findViewById<View>(R.id.username) as TextView
        Link = findViewById<View>(R.id.link) as TextView
        Followers = findViewById<View>(R.id.followers) as TextView


        val avatarUrl = intent.extras!!.getString("avatar_url")
        val link = intent.extras!!.getString("html_url")

        val temp = numberFollowers
        Log.d("temp assigned numfoll", "Assigned to $temp")


        Link!!.text = link
        Linkify.addLinks(Link!!, Linkify.WEB_URLS)

        Username!!.text = username



        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.app_icon)
            .into(imageView!!)
        supportActionBar!!.title = "Details Activity"
    }

    private fun getFollowers(user: String) {
        val loginurl = "/users/$user/followers"
        val Client = Client()
        val apiService2 = Client.getClient()!!.create(Service::class.java)
        val followers: Call<List<Item>> = apiService2.getFollowers(loginurl)
        followers.enqueue(object : Callback<List<Item>> {
            override fun onResponse(
                followers: Call<List<Item>>,
                response: Response<List<Item>>
            ) {
                Log.d("response body", "Retrieved " + response.body())
                if(response.body()?.size != null) {
                    numberFollowers = response.body()?.size!!
                }

                Log.d("numberFollowers", "Retrieved $numberFollowers")
                Followers!!.text = numberFollowers.toString()
                Log.d("temp to string", "Returned  $numberFollowers")
            }

            override fun onFailure(followers: Call<List<Item>?>?, t: Throwable) {
                Toast.makeText(applicationContext, "Error Fetching Data!", Toast.LENGTH_SHORT)
                    .show()
                noConnection.visibility = View.VISIBLE
            }
        })
    }


    private fun createShareIntent(): Intent {
        val username = intent.extras!!.getString("login")
        val link = intent.extras!!.getString("link")

        return ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setText("Check out this awesome developer @$username, $link")
            .intent
    }
}