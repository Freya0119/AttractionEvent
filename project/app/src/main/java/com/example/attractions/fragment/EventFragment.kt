package com.example.attractions.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.attractions.databinding.EventDetailBinding

class EventFragment : Fragment() {
    private var _binding: EventDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = EventDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}