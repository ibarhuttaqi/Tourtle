package com.example.tourtle.ui.home.destination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.tourtle.databinding.ItemDestinationBinding

class DestinationAdapter(private val destinations: List<Destination>) :
    RecyclerView.Adapter<DestinationAdapter.DestinationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        val binding = ItemDestinationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DestinationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        val destination = destinations[position]
        holder.bind(destination)
    }

    override fun getItemCount(): Int = destinations.size

    inner class DestinationViewHolder(private val binding: ItemDestinationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(destination: Destination) {
//            val radius = 30
//            Glide.with(binding.destinationImage.context)
//                .load(destination.imageResId)
//                .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
//                .into(binding.destinationImage)

            binding.destinationImage.setImageResource(destination.imageResId)
            binding.destinationTitle.text = destination.name
            binding.destinationLocation.text = destination.location
            binding.destinationTime.text = destination.hours
            binding.destinationPrice.text = destination.price
        }
    }
}