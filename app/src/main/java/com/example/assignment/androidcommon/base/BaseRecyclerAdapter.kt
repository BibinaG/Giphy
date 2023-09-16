package com.example.assignment.androidcommon.base

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class ImmutableRecyclerAdapter<T, VB> :
    RecyclerView.Adapter<VBViewHolder<VB>>(), ImmutableAutoUpdatableAdapter {
    abstract var items: List<T>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VBViewHolder<VB> {
        return VBViewHolder(getViewBinding(parent))
    }

    override fun getItemCount() = items.size

    abstract fun getViewBinding(parent: ViewGroup): VB

    fun addItem(vararg newItems: T) {
        val list = items.toMutableList()
        list.addAll(newItems)
        items = list
    }

    fun addItems(newItems: List<T>) {
        val list = items.toMutableList()
        list.addAll(newItems)
        items = list
    }

    fun removeAll(){
        items = emptyList()
    }

    fun remove(item: T) {
        val list = items.toMutableList()
        list.remove(item)
        items = list
    }

    fun removeAt(position: Int) {
        val list = items.toMutableList()
        list.removeAt(position)
        items = list
    }
}

interface ImmutableAutoUpdatableAdapter {
    fun <T> RecyclerView.Adapter<*>.autoNotify(
        old: List<T>,
        new: List<T>,
        compare: (T, T) -> Boolean
    ) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(old[oldItemPosition], new[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] == new[newItemPosition]
            }

            override fun getOldListSize() = old.size

            override fun getNewListSize() = new.size
        })
        diff.dispatchUpdatesTo(this)
    }
}

class VBViewHolder<VB>(val binding: VB) : RecyclerView.ViewHolder((binding as ViewBinding).root)