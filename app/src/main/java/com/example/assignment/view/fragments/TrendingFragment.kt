package com.example.assignment.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.assignment.androidcommon.utils.UiState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.androidcommon.base.BaseFragment
import com.example.assignment.databinding.FragmentTrendingBinding
import com.example.assignment.view.adapter.FirstFragmentAdapter
import com.example.assignment.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {
    private var _binding: FragmentTrendingBinding? = null
    private val trendVM by viewModel<TrendingViewModel>()
    private lateinit var trendingGifAdapter: FirstFragmentAdapter
    private lateinit var searchResultAdapter: FirstFragmentAdapter
    private var currentOffset = 0;


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentTrendingBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root
        initViews()
        initObserver()
        searchCall()
    }

    private fun initViews() {
        trendingGifAdapter = FirstFragmentAdapter(
            onFavClick = { item ->
                trendVM.insert(item)
                Toast.makeText(requireContext(), "Liked Giphy", Toast.LENGTH_SHORT).show()
            }
        )
        searchResultAdapter = FirstFragmentAdapter(
            onFavClick = { item ->
                trendVM.insert(item)
                Toast.makeText(requireContext(), "Liked Giphy", Toast.LENGTH_SHORT).show()

            }
        )
        binding.rvSearchResult.visibility = View.INVISIBLE
        binding.rvTrendingGifs.adapter = trendingGifAdapter
        binding.rvSearchResult.adapter = searchResultAdapter

        binding.rvTrendingGifs.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                    // Fetch new data
                    val totalItemsServer =
                        trendVM.trendingGiphy.value?.data?.pagination?.totalCount ?: 0

                    if (totalItemCount < totalItemsServer) {
                        trendVM.getAllTrendingGiphy(totalItemCount)
                    }
                }
            }
        })

        binding.rvSearchResult.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (visibleItemCount + firstVisibleItem >= totalItemCount) {
                    // Fetch new data
                    val totalItemsServer =
                        trendVM.searchResultGifs.value?.data?.pagination?.totalCount ?: 0

                    if (totalItemCount < totalItemsServer) {
                        currentOffset = totalItemCount
                        trendVM.getSearchItem(
                            searchValue = binding.etSearch.text.toString(),
                            offset = totalItemCount
                        )
                    }
                }
            }
        })

        trendVM.getAllTrendingGiphy(0)


    }

    private fun searchCall() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val newText = s.toString()
                if (newText.isEmpty()) {
                    binding.rvSearchResult.visibility = View.INVISIBLE
                    binding.rvTrendingGifs.visibility = View.VISIBLE
                    return
                }
                trendVM.getSearchItem(newText, 0)
                currentOffset = 0
            }
        })
    }


    private fun initObserver() {
        trendVM.trendingGiphy.observe(viewLifecycleOwner) {
            it ?: return@observe
            when (it) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.data?.let { data ->
                        trendingGifAdapter.addItems(data)
                    }
                }

                is UiState.Error -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> Unit
            }
        }

        trendVM.searchResultGifs.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is UiState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.data?.let { data ->
                        if (currentOffset == 0) {
                            searchResultAdapter.removeAll()
                        }
                        if (data.isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "No Search result found",
                                Toast.LENGTH_SHORT
                            ).show()
                            searchResultAdapter.removeAll()
                            return@observe
                        }
                        binding.rvSearchResult.visibility = View.VISIBLE
                        binding.rvTrendingGifs.visibility = View.INVISIBLE
                        searchResultAdapter.addItems(data)
                    }
                }

                is UiState.Error -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> {
                    binding.rvSearchResult.visibility = View.INVISIBLE
                    binding.rvTrendingGifs.visibility = View.VISIBLE
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}