package com.example.tourtle.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourtle.R

data class Article(val image: Int, val title: String, val summary: String)

class ArticleAdapter(private val list: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleImage: ImageView = view.findViewById(R.id.articleImage)
        val articleTitle: TextView = view.findViewById(R.id.articleTitle)
        val articleSummary: TextView = view.findViewById(R.id.articleSummary)
        val readMoreButton: Button = view.findViewById(R.id.readMoreButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.articleImage.setImageResource(item.image)
        holder.articleTitle.text = item.title
        holder.articleSummary.text = item.summary
        holder.readMoreButton.setOnClickListener {
            // Handle read more button click
        }
    }

    override fun getItemCount() = list.size
}