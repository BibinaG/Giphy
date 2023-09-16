package com.example.assignment.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.assignment.androidcommon.base.ImmutableRecyclerAdapter
import com.example.assignment.androidcommon.base.VBViewHolder
import com.example.assignment.databinding.GifyItemsBinding
import kotlin.properties.Delegates

//class SearchResultAdapter :
//    ImmutableRecyclerAdapter<SearchData, GifyItemsBinding>() {
//    override var items: List<SearchData> by Delegates.observable(emptyList()) { _, old, new ->
//        autoNotify(old, new) { o, n -> o == n }
//    }
//
//    override fun getViewBinding(parent: ViewGroup): GifyItemsBinding {
//        return GifyItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    }
//
//    override fun onBindViewHolder(holder: VBViewHolder<GifyItemsBinding>, position: Int) {
////        val fictionData: GifyItemsBinding. = items[position]
//        with(holder.binding) {
//
//
//        }
//    }
//}