package com.example.tourtle.ui.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.tourtle.MainActivity
import com.example.tourtle.MainViewModel
import com.example.tourtle.R
import com.example.tourtle.ViewModelFactory
import com.example.tourtle.databinding.ActivitySplashBinding
import com.example.tourtle.ui.welcome.WelcomeActivity
import kotlin.random.Random

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mediaPlayer: MediaPlayer

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val pref = ThemePreferences.getInstance(application.dataStore)
//        val themeViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
//            ThemeViewModel::class.java
//        )
        val themeViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(
            ThemeViewModel::class.java
        )

        // Mendapatkan pengaturan tema
        themeViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.ivTourtleSplash.setImageResource(R.drawable.logo_tourtle)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.ivTourtleSplash.setImageResource(R.drawable.logo_tourtle)
            }
        }

        // Tunda selama 3 detik
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getSession().observe(this) { user ->
                if (!user.isLogin) {
                    startActivity(Intent(this, WelcomeActivity::class.java))
                    finish()
                } else {
                    viewModel.setToken(user.token)
                    val token = viewModel.token.value
                    Log.d("LOGINTOKEN main activity", "Token: $token")

                    viewModel.token.observe(this) { token ->
                        if (token.isNotEmpty()) {
    //                        observeStories(token) // Call observeStories with the token
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    }
                }
            }
//            val intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }, 2000)

        setupView()
        playAnimation()
    }

    private fun playAnimation() {
//        mediaPlayer = MediaPlayer.create(this, R.raw.cling_sound)

        // Animasi Skala dan Fade In
        val scaleXAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleX", 0.5f, 1f).apply {
            duration = 2000
        }

        val scaleYAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleY", 0.5f, 1f).apply {
            duration = 2000
        }

        val alphaAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "alpha", 0f, 1f).apply {
            duration = 2000
        }

        // Detakan terakhir yang lebih besar
        val finalScaleXAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleX", 1f, 1.2f).apply {
            duration = 500
            startDelay = 2000
        }

        val finalScaleYAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleY", 1f, 1.2f).apply {
            duration = 500
            startDelay = 2000
        }

        val finalAlphaAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "alpha", 1f, 1f).apply {
            duration = 500
            startDelay = 2000
        }

        val finalAnimatorSet = AnimatorSet().apply {
            playTogether(finalScaleXAnimator, finalScaleYAnimator, finalAlphaAnimator)
        }

        AnimatorSet().apply {
            playTogether(scaleXAnimator, scaleYAnimator, alphaAnimator)
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
//                    mediaPlayer.start()
                }

                override fun onAnimationEnd(animation: Animator) {
                    finalAnimatorSet.start()
                }
            })
        }
    }

//    private fun playAnimation() {
//        // Animasi Muncul dan Membesar
//        val alphaAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "alpha", 0f, 1f).apply {
//            duration = 1500
//        }
//
//        val scaleXAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleX", 1f, 1.5f).apply {
//            duration = 1500
//        }
//
//        val scaleYAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleY", 1f, 1.5f).apply {
//            duration = 1500
//        }
//
//        AnimatorSet().apply {
//            playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator)
//            start()
//        }
//    }



//    private fun playAnimation() {
//        // Animasi Bergelombang dan Berdetak
//        val scaleXAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleX", 1f, 1.1f).apply {
//            duration = 1000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }
//
//        val scaleYAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleY", 1f, 1.1f).apply {
//            duration = 1000
//            repeatCount = ObjectAnimator.INFINITE
//            repeatMode = ObjectAnimator.REVERSE
//        }
//
//        // Animasi Gelombang
//        val waveAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "alpha", 0f, 1f).apply {
//            duration = 500
//            repeatCount = 1
//            repeatMode = ObjectAnimator.REVERSE
//        }
//
//        waveAnimator.addListener(object : Animator.AnimatorListener {
//            override fun onAnimationStart(animation: Animator) {
//                binding.ivTourtleSplash.visibility = View.VISIBLE
////                mediaPlayer = MediaPlayer.create(this@SplashActivity, R.raw.cling_sound)
////                mediaPlayer.start()
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                binding.ivTourtleSplash.visibility = View.VISIBLE
////                mediaPlayer.release()
//            }
//
//            override fun onAnimationCancel(animation: Animator) {}
//            override fun onAnimationRepeat(animation: Animator) {}
//        })
//
//        // Animasi Membesar dan Jelas di Akhir
//        val finalScaleXAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleX", 1f, 1.5f).apply {
//            duration = 1000
//            startDelay = 1500
//        }
//
//        val finalScaleYAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "scaleY", 1f, 1.5f).apply {
//            duration = 1000
//            startDelay = 1500
//        }
//
//        val finalAlphaAnimator = ObjectAnimator.ofFloat(binding.ivTourtleSplash, "alpha", 0.5f, 1f).apply {
//            duration = 1000
//            startDelay = 1500
//        }
//
//        AnimatorSet().apply {
//            playTogether(scaleXAnimator, scaleYAnimator, waveAnimator, finalScaleXAnimator, finalScaleYAnimator, finalAlphaAnimator)
//            start()
//        }
//
//        Handler(Looper.getMainLooper()).postDelayed({
//            waveAnimator.start()
//        }, 1000)
//    }

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
}