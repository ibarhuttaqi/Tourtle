package com.example.tourtle.ui.tour_guide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tourtle.databinding.ItemTourGuideBinding
import com.example.tourtle.ui.home.tour_guide.TourGuide

class TourGuideAdapter(private val tourGuides: List<TourGuide>) :
    RecyclerView.Adapter<TourGuideAdapter.TourGuideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourGuideViewHolder {
        val binding = ItemTourGuideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourGuideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TourGuideViewHolder, position: Int) {
        val tourGuide = tourGuides[position]
        holder.bind(tourGuide)
    }

    override fun getItemCount(): Int = tourGuides.size

    inner class TourGuideViewHolder(private val binding: ItemTourGuideBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tourGuide: TourGuide) {
            Glide.with(binding.tourGuideImage.context)
                .load(tourGuide.imageResId)
                .into(binding.tourGuideImage)
            binding.tourGuideName.text = tourGuide.name
            // Bind data lainnya sesuai dengan layout yang telah dibuat
        }
    }
}
