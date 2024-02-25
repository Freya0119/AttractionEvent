package com.example.attractions

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager.widget.ViewPager
import com.example.attractions.databinding.MainActivityBinding
import com.example.attractions.fragment.AttractionContainerFragment
import com.example.attractions.fragment.AttractionListFragment
import com.example.attractions.fragment.EventListFragment
import com.example.attractions.viewmodel.AttractionViewModel
import com.example.attractions.viewmodel.EventViewModel

val lang = listOf("zh-tw", "zh-cn", "en", "ja", "ko", "es", "id", "th", "vi")

class MainActivity : AppCompatActivity() {
    //  binding 保證 NotNull
    private lateinit var _binding: MainActivityBinding
    private val binding get() = _binding

    //    TODO: liftCycleOwner
    private val attractionViewModels: AttractionViewModel by viewModels<AttractionViewModel>()
    private val eventViewModels: EventViewModel by viewModels<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pagerAddFragment(binding.viewPager)
        binding.mainTabs.setupWithViewPager(binding.viewPager)
    }

    private fun pagerAddFragment(pager: ViewPager) {
        val viewPagerAdapter = ViewPager2Adapter(supportFragmentManager)
        viewPagerAdapter.addFragment(AttractionContainerFragment())
        viewPagerAdapter.addFragment(EventListFragment())
        pager.adapter = viewPagerAdapter
    }
}

class ViewPager2Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private var fragments: MutableList<Fragment>  = mutableListOf()

    init {
        Log.d("PAGER", "Init fragment")
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return position.toString()
    }

    fun addFragment(fragment: Fragment) {
        Log.d("PAGER", "Add fragment")
        fragments.add(fragment)
    }
}
