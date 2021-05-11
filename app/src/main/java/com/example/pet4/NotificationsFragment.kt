package com.example.pet4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pet4.databinding.NotificationsFragmentBinding

class NotificationsFragment: Fragment() {
    lateinit var binding: NotificationsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotificationsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}