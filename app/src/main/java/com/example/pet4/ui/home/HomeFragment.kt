package com.example.pet4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.*
import com.example.pet4.data.models.Place
import com.example.pet4.databinding.HomeFragmentBinding
import com.example.pet4.ui.home.adapters.MyAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.*

/**
 * Первый фрагмент. В его recyclerView забиваем PagingData
 */
class HomeFragment: Fragment() {

    lateinit var binding: HomeFragmentBinding
    private lateinit var viewModel: HomeFragmentViewModelImpl

    object UserComparator : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.coord.lon == newItem.coord.lon
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.name.equals(newItem.name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater).apply {
            viewModel = ViewModelProvider(this@HomeFragment).get(HomeFragmentViewModelImpl::class.java)
        }
        return binding.root
    }


    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = binding.viewModel!!

        val navController = findNavController()

        val adapter = MyAdapter(UserComparator, navController)

        val recyclerView = binding.articlesRecycler

        val layoutManager = GridLayoutManager(context, 1)

        recyclerView.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.getContext(),
            layoutManager.getOrientation()
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getFlow().distinctUntilChanged().collectLatest{
                adapter.submitData(it)
            }
            }
        }
}