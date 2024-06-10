package com.example.tourtle

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tourtle.databinding.ActivityMainBinding
import com.example.tourtle.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_forum, R.id.navigation_smart_camera, R.id.navigation_my_journey, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // melakukan penyesuaian tab yang aktif akan mengikuti parent fragment saat child fragment diklik
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_destination -> navView.menu.findItem(R.id.navigation_home).isChecked = true
                R.id.navigation_tour_guide -> navView.menu.findItem(R.id.navigation_home).isChecked = true
                R.id.photoPreviewFragment -> navView.menu.findItem(R.id.navigation_smart_camera).isChecked = true
                else -> navView.menu.findItem(destination.id).isChecked = true
            }
        }

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                viewModel.setToken(user.token)
//                val token = viewModel.token.value
//                Log.d("LOGINTOKEN main activity", "Token: $token")
//                viewModel.token.observe(this) { token ->
//                    if (token.isNotEmpty()) {
////                        observeStories(token) // Call observeStories with the token
//                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
//                    }
//                }

            }
        }

        setupView()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (navController.currentDestination?.id == R.id.navigation_home) {
            finishAffinity() // This will close all activities and exit the app
        } else {
            if (navController.popBackStack()) {
                // Do nothing, let the navController handle the back stack
            } else {
                super.onBackPressed() // This will handle navigation back stack
            }
        }
    }
}