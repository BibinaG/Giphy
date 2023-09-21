package com.example.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.assignment.R
import com.example.assignment.androidcommon.base.ImmutableRecyclerAdapter
import com.example.assignment.androidcommon.base.VBViewHolder
import com.example.assignment.databinding.GifyItemsBinding
import com.example.assignment.model.table.TrendingTable
import kotlin.properties.Delegates

class FavoriteAdapter(val onFavClick: (TrendingTable) -> Unit) :
    ImmutableRecyclerAdapter<TrendingTable, GifyItemsBinding>() {
    override var items: List<TrendingTable> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun getViewBinding(parent: ViewGroup): GifyItemsBinding {
        return GifyItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindViewHolder(holder: VBViewHolder<GifyItemsBinding>, position: Int) {
        val item = items[position]
        with(holder.binding) {
            Glide.with(holder.itemView.context)
                .load(item.url)
                .error(R.drawable.flat_tick)
                .into(ivGif)
            tvName.text = item.title
            like.setOnClickListener {
                onFavClick(item)
            }
        }

    }

}