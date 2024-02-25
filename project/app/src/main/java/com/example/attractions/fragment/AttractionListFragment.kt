package com.example.attractions.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.attractions.R
import com.example.attractions.adapter.ShowListAdapter
import com.example.attractions.databinding.AttractionListBinding
import com.example.attractions.viewmodel.AttractionViewModel

const val FRAGMENT_LOG = "FRAGMENT_LOG"

class AttractionListFragment : Fragment() {

    private var _binding: AttractionListBinding? = null
    private val binding get() = _binding!!

    private val viewModels: AttractionViewModel by activityViewModels<AttractionViewModel>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = AttractionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.showListRecyclerView

//  TODO: for test, delete
        view.findViewById<Button>(R.id.main_fragment_textview).setOnClickListener {
            viewModels.getAttractions("en")
        }

        viewModels.uiStateList.observe(viewLifecycleOwner) {
            Log.d(FRAGMENT_LOG, "LiveData changed. First element: ${it[0].toString()}")
            recyclerView.adapter = ShowListAdapter(it)
        }

//        TODO: navigation
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
////        顯示ActionBar?
//        setupActionBarWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}