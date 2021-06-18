package com.example.pet4.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pet4.databinding.ItemFragmentBinding


/**
 * Второй фрагмент. Тут просто еще координаты дописываем.
 */
class ItemFragment: Fragment()  {

    lateinit var binding: ItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ItemFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = requireArguments().getString("NAME", "def")
        val lat = requireArguments().getDouble("LAT", 0.0)
        val lon = requireArguments().getDouble("LON", 0.0)
        val coord = "lat = $lat; lon = $lon"

        binding.itemName.text = name
        binding.itemCoord.text = coord
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

}