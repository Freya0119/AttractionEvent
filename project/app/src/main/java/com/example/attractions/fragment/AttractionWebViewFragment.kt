package com.example.attractions.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.attractions.databinding.AttractionDetailWebViewBinding

const val WEB_URL = "WEB_URL"

class AttractionWebViewFragment : Fragment() {
    private var _binding: AttractionDetailWebViewBinding? = null
    private val binding get() = _binding!!

    private lateinit var webUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments.let {
//            TODO: safe null check
            if (it != null) {
                webUrl = it.getString(WEB_URL).toString()
            }
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AttractionDetailWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webUrl)

//        webClient

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}