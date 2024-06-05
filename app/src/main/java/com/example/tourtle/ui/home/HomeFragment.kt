package com.example.tourtle.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourtle.R
import com.example.tourtle.databinding.FragmentHomeBinding
import com.example.tourtle.ui.home.destination.DestinationFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var promoBannerAdapter: PromoBannerAdapter
    private val promoImages = listOf(
        R.drawable.promo1,
        R.drawable.promo2,
        R.drawable.promo3
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        promoBannerAdapter = PromoBannerAdapter(promoImages)
        binding.viewPager.adapter = promoBannerAdapter

        val tabLayout = binding.indicator

        // Set up TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            // This will not be called for now, since we do not need tab titles
        }.attach()

        // Set up RecyclerView for Destinations
        val rekomendasiDestinasiList = listOf(
            DestinationRecommendation(R.drawable.promo3, "Hawaii Waterpark", "Kolam Renang, Air, Makanan", "Kota Malang, Jawa Timur", "mulai Rp 90.000", "09.00 - 17.00 WIB"),
            DestinationRecommendation(R.drawable.promo2, "Hawaii Waterpark", "Kolam Renang, Air, Makanan", "Kota Malang, Jawa Timur", "mulai Rp 90.000", "09.00 - 17.00 WIB")
        )

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = DestinationRecommendationAdapter(rekomendasiDestinasiList)

        // Set up RecyclerView for Tour Guides
        val tourGuideList = listOf(
            TourGuide(R.drawable.promo2, "Ibar Huttaqi Sulth...", "Kolam Renang, Air, Makanan", "Kota Malang, Jawa Timur", "mulai Rp 90.000", 4.5f),
            TourGuide(R.drawable.promo1, "Ibar Huttaqi Sulth...", "Kolam Renang, Air, Makanan", "Kota Malang, Jawa Timur", "mulai Rp 90.000", 4.5f)
        )

        val recyclerViewTourGuide = binding.recyclerViewTourGuide
        recyclerViewTourGuide.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTourGuide.adapter = TourGuideAdapter(tourGuideList)

        // Set up RecyclerView for Articles
        val articleList = listOf(
            Article(R.drawable.promo3, "Tips", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Article(R.drawable.promo2, "Berita", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Article(R.drawable.promo1, "Tips", "Lorem ipsum dolor sit amet, consectetur adipiscing elit."),
            Article(R.drawable.promo3, "Berita", "Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
        )

        val recyclerViewArticles = binding.recyclerViewArticles
        recyclerViewArticles.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerViewArticles.adapter = ArticleAdapter(articleList)

        setupAction()

        return root
    }

    private fun setupAction() {
        binding.menuDestination.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_destinationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}