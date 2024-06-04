package com.example.tourtle.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourtle.R

data class TourGuide(val image: Int, val name: String, val tags: String, val location: String, val price: String, val rating: Float)

class TourGuideAdapter(private val list: List<TourGuide>) : RecyclerView.Adapter<TourGuideAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleView: TextView = view.findViewById(R.id.titleView)
        val tagsView: TextView = view.findViewById(R.id.tagsView)
        val locationView: TextView = view.findViewById(R.id.locationView)
        val priceView: TextView = view.findViewById(R.id.priceView)
        val ratingView: TextView = view.findViewById(R.id.ratingView)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val iconHeart: ImageView = view.findViewById(R.id.iconHeart)
        val iconGender: ImageView = view.findViewById(R.id.iconGender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rekomendasi_tour_guide, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.imageView.setImageResource(item.image)
        holder.titleView.text = item.name
        holder.tagsView.text = item.tags
        holder.locationView.text = item.location
        holder.priceView.text = item.price
        holder.ratingView.text = "(${item.rating}/5)"
        holder.ratingBar.rating = item.rating
        // Set other icons as needed
    }

    override fun getItemCount() = list.size
}