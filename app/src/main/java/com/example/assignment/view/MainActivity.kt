package com.example.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.view.adapter.ViewPagerStateAdapter
import com.example.assignment.view.fragments.FirstFragment
import com.example.assignment.view.fragments.SecondFragment
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
        First("FirstFragment", FirstFragment()),
        Second("SecondFragment", SecondFragment()),
    }
}