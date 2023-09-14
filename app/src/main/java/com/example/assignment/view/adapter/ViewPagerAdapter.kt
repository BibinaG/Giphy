package com.example.assignment.view.adapter

import com.example.assignment.view.fragments.FirstFragment
import com.example.assignment.view.fragments.SecondFragment


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerStateAdapter(
    private val fragments: List<Fragment>,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragments.size
    override fun createFragment(position: Int) = fragments[position]

}
