package com.example.tourtle.ui.forum

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourtle.ViewModelFactory
import com.example.tourtle.databinding.FragmentForumBinding
import com.example.tourtle.ui.loading.LoadingStateAdapter
import com.example.tourtle.ui.welcome.WelcomeActivity

class ForumFragment : Fragment() {

    private var _binding: FragmentForumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var forumAdapter: ForumAdapter

    private val viewModel by viewModels<ForumViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
//private val viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireContext())).get(ForumViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val forumViewModel =
//            ViewModelProvider(this).get(ForumViewModel::class.java)

        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        forumViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (!user.isLogin) {
                Log.d("BelumLogin", "User: $user")
                startActivity(Intent(requireContext(), WelcomeActivity::class.java))
                activity?.finish()
            } else {
                Log.d("LOGINTOKEN", "Token: ${user.token}")
//                viewModel.setToken(user.token)
//                viewModel.token.observe(viewLifecycleOwner) { token ->
                        observeForum(user.token)
//                }

            }
        }

        forumAdapter = ForumAdapter()
        binding.rvForum.layoutManager = LinearLayoutManager(requireContext())
        binding.rvForum.adapter = forumAdapter

        return root
    }

    private fun observeForum(token: String) {
        Log.d("Token dari Forum Fragment", "Token: $token")
        binding.rvForum.adapter = forumAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                forumAdapter.retry()
            }
        )

        viewModel.getForum(token).observe(viewLifecycleOwner) { pagingData ->
            forumAdapter.submitData(lifecycle, pagingData)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}