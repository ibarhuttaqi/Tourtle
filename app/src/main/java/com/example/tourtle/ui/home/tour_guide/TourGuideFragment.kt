package com.example.tourtle.ui.home.tour_guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tourtle.R
import com.example.tourtle.databinding.FragmentTourGuideBinding
import com.example.tourtle.ui.tour_guide.TourGuideAdapter

data class TourGuide(
    val name: String,
    val imageResId: Int
    // Tambahkan properti lain yang diperlukan
)

class TourGuideFragment : Fragment() {

    private var _binding: FragmentTourGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTourGuideBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi data pemandu wisata
        val tourGuides = listOf(
            TourGuide("Ibar Huttaqi Sultan", R.drawable.profile1),
            TourGuide("Wawa", R.drawable.profile1),
            // Tambahkan data lainnya
        )

        val adapter = TourGuideAdapter(tourGuides)
        binding.tourGuideRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.tourGuideRecyclerView.adapter = adapter

        // Handle back button
        binding.backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            TourGuideFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }
}