package com.example.attractions.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.attractions.adapter.ShowListAdapter
import com.example.attractions.databinding.EventListBinding
import com.example.attractions.viewmodel.AttractionViewModel
import com.example.attractions.viewmodel.EventViewModel

class EventListFragment : Fragment() {
    private var _binding: EventListBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    private val viewModels: EventViewModel by activityViewModels<EventViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = EventListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.eventListRecyclerView
//        TODO: adapter
        Log.d("EVENT_LOG", "Event view created.")
        viewModels.getEvents("ja")

        viewModels.uiState.observe(viewLifecycleOwner) {
            Log.d("EVENT_LOG", "Event recycler changed.")
            binding.eventTextView.text = viewModels.uiState.value!![0].title
            val adapter = ShowListAdapter(it)
            recyclerView.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}