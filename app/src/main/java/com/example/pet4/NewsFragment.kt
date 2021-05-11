package com.example.pet4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pet4.databinding.NewsFragmentBinding

class NewsFragment: Fragment() {

    lateinit var binding: NewsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}