package com.example.attractions.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import coil.load
import com.example.attractions.R
import com.example.attractions.data.AttractionDetailState
import com.example.attractions.databinding.AttractionDetailFragmentBinding
import com.example.attractions.databinding.AttractionDetailWebViewBinding
import com.example.attractions.viewmodel.AttractionViewModel

//  TODO: KEY
const val KEY = "KEY_STRING"

class AttractionDetailFragment : Fragment() {
    private var _binding: AttractionDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModels: AttractionViewModel by activityViewModels<AttractionViewModel>()

    private lateinit var attractionDetailUi: AttractionDetailState

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
//            attractionDetailUi = viewModels.setAttraction(it.getString(KEY) ?: "No data")
        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AttractionDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindView(view: View) {
//        TODO: more images & no image src -> { }
        binding.attractioonDetailImage.load(attractionDetailUi.images[0].src)
        binding.attractionDetailTitle.text = attractionDetailUi.name
//        TODO: webView, Javascript
        binding.attractionDetailUrl.text = attractionDetailUi.url
        binding.attractionDetailUrl.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("WEB_URL", attractionDetailUi.url)
//            TODO: view ?
            Navigation.findNavController(view).navigate(R.id.action_attractionDetailFragment_to_attractionWebViewFragment, bundle)
        }

        binding.attractionDetailIntroduction.text = attractionDetailUi.introduction
    }
}
