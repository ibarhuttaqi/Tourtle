package com.example.tourtle.ui.welcome

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tourtle.R
import com.example.tourtle.databinding.ActivityWelcomeBinding
import com.example.tourtle.ui.login.LoginActivity
import com.example.tourtle.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupView()
        setupAction()
        playAnimation()
        Toast.makeText(this, "WELCOMEEEEE", Toast.LENGTH_LONG).show()

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

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.signupButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //skip transition
        binding.skipTextView.setOnClickListener {
//            binding.main.transitionToState(R.id.menu4, 2000)

//            // Start the sequence of transitions
//            transitionThroughStates()

            when (binding.main.currentState) {
                R.id.end -> transitionToEndStartingFrom(R.id.menu2)
                R.id.menu2 -> transitionToEndStartingFrom(R.id.menu3)
                R.id.menu3 -> transitionToEndStartingFrom(R.id.menu4)
                R.id.menu4 -> {} // Do nothing if already at menu4
            }
        }
    }

    private fun playAnimation() {
        val menuLogos = listOf(binding.logoMenu1, binding.logoMenu2, binding.logoMenu3, binding.logoMenu4)
        menuLogos.forEach { menuLogo ->
            ObjectAnimator.ofFloat(menuLogo, View.TRANSLATION_X, -50f, 50f).apply {
                duration = 6000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
            }.start()
        }
    }

    fun transitionThroughStates() {
        val transitionTime = 500 // Transition time for each step in milliseconds
        binding.main.setTransitionDuration(transitionTime)

        // Sequentially transition to each state
        binding.main.transitionToState(R.id.menu1)
        binding.main.postDelayed({ binding.main.transitionToState(R.id.menu2) }, transitionTime.toLong())
        binding.main.postDelayed({ binding.main.transitionToState(R.id.menu3) }, 2 * transitionTime.toLong())
        binding.main.postDelayed({ binding.main.transitionToState(R.id.menu4) }, 3 * transitionTime.toLong())
    }

    fun transitionToEndStartingFrom(startStateId: Int) {
        val transitionTime = 500 // Adjust this value as needed
        when (startStateId) {
            R.id.menu2 -> {
                binding.main.transitionToState(R.id.menu2)
                binding.main.postDelayed({ binding.main.transitionToState(R.id.menu3) }, transitionTime.toLong())
                binding.main.postDelayed({ binding.main.transitionToState(R.id.menu4) }, 2 * transitionTime.toLong())
            }
            R.id.menu3 -> {
                binding.main.transitionToState(R.id.menu3)
                binding.main.postDelayed({ binding.main.transitionToState(R.id.menu4) }, transitionTime.toLong())
            }
            R.id.menu4 -> {
                binding.main.transitionToState(R.id.menu4)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() // This will close all activities and exit the app
    }
}