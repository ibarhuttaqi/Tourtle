package com.example.tourtle.ui.home.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tourtle.R
import com.example.tourtle.databinding.FragmentDestinationBinding
import com.google.android.material.tabs.TabLayoutMediator

class DestinationFragment : Fragment() {

    private var _binding: FragmentDestinationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDestinationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabTitles = listOf("Semua", "Wisata", "Kuliner", "Rekomendasi")

//        val adapter = ViewPagerAdapter(requireActivity())
//        binding.viewPager.adapter = adapter
//
//        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = tabTitles[position]
//        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
//    private val fragmentList = listOf(
//        AllDestinationsFragment(),
//        TourismFragment(),
//        CulinaryFragment(),
//        RecommendationFragment()
//    )
//
//    override fun getItemCount(): Int {
//        return fragmentList.size
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return fragmentList[position]
//    }
//}