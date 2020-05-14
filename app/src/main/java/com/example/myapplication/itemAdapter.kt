package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.controller.DetailActivity
import com.example.myapplication.model.Item
import com.squareup.picasso.Picasso



class ItemAdapter(
    applicationContext: Context,
    itemArrayList: List<Item>
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val items: List<Item> = itemArrayList
    private val context: Context = applicationContext
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_user, viewGroup, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.title.text = items[i].getLogin()
        viewHolder.githublink1.text = items[i].getHtmlUrl()
        Picasso.get()
            .load(items[i].getAvatarUrl())
            .placeholder(R.drawable.app_icon)
            .into(viewHolder.imageView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val githublink1: TextView = view.findViewById(R.id.githubLink1)
        val imageView: ImageView = view.findViewById(R.id.profilePicture) as ImageView

        init {

            //on item click
            itemView.setOnClickListener {
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem: Item =
                        items[pos] //import the models item from project
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("login", items[pos].getLogin())
                    intent.putExtra("html_url", items[pos].getHtmlUrl())
                    intent.putExtra("avatar_url", items[pos].getAvatarUrl())
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                    Toast.makeText(
                        view.context,
                        "You clicked " + clickedDataItem.getLogin(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}
