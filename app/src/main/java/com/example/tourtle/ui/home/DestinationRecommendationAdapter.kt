package com.example.tourtle.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourtle.R

data class DestinationRecommendation(val image: Int, val title: String, val tags: String, val location: String, val price: String, val time: String)

class DestinationRecommendationAdapter(private val list: List<DestinationRecommendation>) : RecyclerView.Adapter<DestinationRecommendationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val titleView: TextView = view.findViewById(R.id.titleView)
        val tagsView: TextView = view.findViewById(R.id.tagsView)
        val locationView: TextView = view.findViewById(R.id.locationView)
        val priceView: TextView = view.findViewById(R.id.priceView)
        val timeView: TextView = view.findViewById(R.id.timeView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rekomendasi_destinasi, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.imageView.setImageResource(item.image)
        holder.titleView.text = item.title
        holder.tagsView.text = item.tags
        holder.locationView.text = item.location
        holder.priceView.text = item.price
        holder.timeView.text = item.time
    }

    override fun getItemCount() = list.size
}