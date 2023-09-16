package com.example.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.androidcommon.base.ImmutableRecyclerAdapter
import com.example.assignment.androidcommon.base.VBViewHolder
import com.example.assignment.databinding.GifyItemsBinding
import com.example.assignment.model.TrendingResponse
import kotlin.properties.Delegates

class FirstFragmentAdapter() : ImmutableRecyclerAdapter<TrendingResponse, GifyItemsBinding>() {
    override var items: List<TrendingResponse> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun getViewBinding(parent: ViewGroup): GifyItemsBinding {
        return GifyItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: VBViewHolder<GifyItemsBinding>, position: Int) {
        val item = items[position]
        with(holder.binding){
            Glide.with(holder.itemView.context)
                .load(item.image?.downsized?.mp4)
                .error(R.drawable.flat_tick)
                .into(ivGif)

            tvName.text = item.title
        }
    }

}