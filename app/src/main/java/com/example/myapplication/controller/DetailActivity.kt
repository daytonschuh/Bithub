package com.example.myapplication.controller

import android.content.Intent
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.example.myapplication.R


class DetailActivity : AppCompatActivity() {
    var Link: TextView? = null
    var Username: TextView? = null
    var mActionBarToolbar: Toolbar? = null
    var imageView: ImageView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_page)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        imageView = findViewById<View>(R.id.userImageHeader) as ImageView
        Username = findViewById<View>(R.id.username) as TextView

        Link = findViewById<View>(R.id.link) as TextView

        val username = intent.extras!!.getString("login")
        val avatarUrl = intent.extras!!.getString("avatar_url")
        val link = intent.extras!!.getString("html_url")

        Link!!.text = link
        Linkify.addLinks(Link!!, Linkify.WEB_URLS)

        Username!!.text = username
        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.app_icon)
            .into(imageView!!)
        supportActionBar!!.title = "Details Activity"
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