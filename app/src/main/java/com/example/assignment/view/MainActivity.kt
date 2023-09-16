package com.example.assignment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.view.adapter.ViewPagerStateAdapter
import com.example.assignment.view.fragments.TrendingFragment
import com.example.assignment.view.fragments.FavouritesFragment
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

    }


    private fun initView() {
        with(binding) {
            val fragments = GiphyFragments.values().map { it.fragment }
            val label = GiphyFragments.values().map { it.label }
            val pagerAdapter = ViewPagerStateAdapter(fragments, this@MainActivity)
            viewPager.adapter = pagerAdapter
            viewPager.offscreenPageLimit = 3

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = label[position]
            }.attach()

        }
    }

    enum class GiphyFragments(val label: String, val fragment: Fragment) {
        First("FirstFragment", TrendingFragment()),
        Second("SecondFragment", FavouritesFragment()),
    }
}