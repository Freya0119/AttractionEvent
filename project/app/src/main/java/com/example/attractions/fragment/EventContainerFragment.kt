package com.example.attractions.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.attractions.R

class EventContainerFragment: Fragment(R.layout.event_container) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.nav_host_fragment_event) as NavHostFragment
        val navController = navHost.navController
    }
}