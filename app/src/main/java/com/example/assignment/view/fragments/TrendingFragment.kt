package com.example.assignment.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignment.androidcommon.utils.UiState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.androidcommon.base.BaseFragment
import com.example.assignment.databinding.FragmentTrendingBinding
import com.example.assignment.network.dao.GiphyDao
import com.example.assignment.network.database.GiphyDatabase
import com.example.assignment.view.adapter.FirstFragmentAdapter
import com.example.assignment.viewmodel.DataTypes
import com.example.assignment.viewmodel.TrendingViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {
    private var _binding: FragmentTrendingBinding? = null
    private val trendVM by viewModel<TrendingViewModel>()
    private lateinit var firstFragmentAdapter: FirstFragmentAdapter


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
        firstFragmentAdapter = FirstFragmentAdapter()
        binding.rvSearchResult.adapter = firstFragmentAdapter
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
                        trendVM.trendingGiphy.value?.data?.pagination?.totalCount ?: 0

                    if (totalItemCount < totalItemsServer) {
                        trendVM.getAllTrendingGiphy(totalItemCount)
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
                var newText = s.toString()
                trendVM.setDataType(DataTypes.Search)
                trendVM.getSearchItem(newText, 0)
            }

            override fun afterTextChanged(s: Editable?) {
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
                        if (trendVM.dataType == DataTypes.Search) {
                            firstFragmentAdapter.removeAll()
                            trendVM.setDataType(DataTypes.Trending)
                            firstFragmentAdapter.addItems(data)

                        }
                        firstFragmentAdapter.addItems(data)
                        GiphyDatabase.getDatabase(requireContext())
                    }
                }

                is UiState.Error -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                else -> Unit
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}