package com.example.tourtle.ui.home.destination

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tourtle.R
import com.example.tourtle.databinding.FragmentDestinationBinding
import com.google.android.material.tabs.TabLayoutMediator

data class Destination(
    val name: String,
    val location: String,
    val hours: String,
    val price: String,
    val imageResId: Int
)

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

        // Setup RecyclerView
        binding.destinationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = DestinationAdapter(getDummyDestinations())
        binding.destinationRecyclerView.adapter = adapter

        // Handle back button
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getDummyDestinations(): List<Destination> {
        // Replace this with actual data retrieval logic
        return listOf(
            Destination("Hawaii Waterpark", "Kota Malang, Jawa Timur", "09.00 - 17.00 WIB", "mulai Rp 90.000", R.drawable.destination1),
            Destination("Sego Sambel Cak Uut", "Kota Malang, Jawa Timur", "09.00 - 21.00 WIB", "mulai Rp 6.000", R.drawable.destination2)
            // Add more dummy destinations here
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
