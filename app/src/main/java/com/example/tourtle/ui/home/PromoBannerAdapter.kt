package com.example.tourtle.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tourtle.R

class PromoBannerAdapter(private val promoImages: List<Int>) : RecyclerView.Adapter<PromoBannerAdapter.PromoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.promo_banner_item, parent, false)
        return PromoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromoViewHolder, position: Int) {
        holder.promoImageView.setImageResource(promoImages[position])
    }

    override fun getItemCount(): Int = promoImages.size

    class PromoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val promoImageView: ImageView = itemView.findViewById(R.id.promoImageView)
    }
}
