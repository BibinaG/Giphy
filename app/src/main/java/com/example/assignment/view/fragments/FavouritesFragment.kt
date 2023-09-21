package com.example.assignment.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.assignment.androidcommon.base.BaseFragment
import com.example.assignment.databinding.FragmentFavouritesBinding
import com.example.assignment.view.adapter.FavoriteAdapter
import com.example.assignment.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouritesFragment : BaseFragment<FragmentFavouritesBinding>() {
    private lateinit var favAdapter: FavoriteAdapter
    private val trendVM by viewModel<TrendingViewModel>()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavouritesBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObserver()
    }

    private fun initViews() {
        favAdapter = FavoriteAdapter { item ->
            trendVM.delete(item)
            Toast.makeText(requireContext(), "Removed liked Giphy", Toast.LENGTH_SHORT).show()

        }
        binding.rvFavGifs.adapter = favAdapter
    }

    private fun initObserver() {
        trendVM.favGifs.observe(viewLifecycleOwner) {
            it ?: return@observe
            favAdapter.items = it
        }
    }

    override fun onResume() {
        super.onResume()
        trendVM.getFavGifs()
    }
}