package com.example.pet4.ui.home.adapters


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pet4.R
import com.example.pet4.data.models.Place
import com.example.pet4.databinding.ItemLayoutBinding


/**
 * Класс адаптера. Передаю сюда navController для перехода на второе окошко
 */
class MyAdapter(diffCallback: DiffUtil.ItemCallback<Place>, val navController: NavController) : PagingDataAdapter<Place, MyAdapter.ArticleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ItemLayoutBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ArticleViewHolder(val dataBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(place: Place) {
            dataBinding.article = place
                dataBinding.root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("NAME", place.name)
                    bundle.putDouble("LAT", place.coord.lat)
                    bundle.putDouble("LON", place.coord.lon)
                    navController.navigate(R.id.action_homeFragment_to_newsFragment, bundle)
                }
        }
    }
}

