package com.example.tourtle.ui.forum

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tourtle.data.api.response.ListForumItem
import com.example.tourtle.databinding.ItemRowForumBinding

class ForumAdapter : PagingDataAdapter<ListForumItem, ForumAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRowForumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val story = getItem(position)
        if (story != null) {
            holder.bind(story)
//            holder.itemView.setOnClickListener {
//                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
//                    putExtra(DetailActivity.EXTRA_STORY, story)
//                }
//                holder.itemView.context.startActivity(intent)
//            }
        }
    }

    class MyViewHolder(val binding: ItemRowForumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(story: ListForumItem) {
            // Bind data to your view elements here
            Glide.with(itemView.context)
                .load(story.photoUrl)
                .into(binding.ivBanner)
            binding.tvUsername.text = story.name
            binding.tvDesc.text = "${story.description}..."
            Log.d(ContentValues.TAG, "Response2: ${story}")
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListForumItem>() {
            override fun areItemsTheSame(oldItem: ListForumItem, newItem: ListForumItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ListForumItem, newItem: ListForumItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}