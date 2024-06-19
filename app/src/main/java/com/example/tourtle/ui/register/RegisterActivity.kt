package com.example.tourtle.ui.register

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.tourtle.R
import com.example.tourtle.data.api.response.RegisterResponse
import com.example.tourtle.data.api.retrofit.ApiConfig
import com.example.tourtle.databinding.ActivityRegisterBinding
import com.example.tourtle.ui.login.LoginActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterActivity : AppCompatActivity() {
private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.nameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().length < 4) {
                    binding.nameEditText.setError("Nama tidak boleh kurang dari 4 karakter", null)
                } else {
                    binding.nameEditText.error = null
                }
            }

            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })

        val loginTextView: TextView = findViewById(R.id.registerTextView)
        val text = "Sudah mempunyai akun? Masuk"
        val spannable = SpannableString(text)

        // Set color for "Sudah mempunyai akun?" to black
        val blackSpan = ForegroundColorSpan(Color.BLACK)
        spannable.setSpan(blackSpan, 0, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set color for "Masuk" to blue
        val blueSpan = ForegroundColorSpan(Color.BLUE)
        spannable.setSpan(blueSpan, 21, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        loginTextView.text = spannable

        loginTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        setupView()
        setupAction()
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            showLoading(true)

            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService("")
                    val successResponse = apiService.register(name, email, password)
                    showToast(successResponse.message, true)
                    showLoading(false)
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
                    showToast(errorResponse.message, false)
                    showLoading(false)
                }
            }
        }
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
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    private fun showToast(message: String, isValid: Boolean) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        if (isValid) {
            AlertDialog.Builder(this).apply {
                setTitle("Yeah!")
                setMessage("Akun anda sudah jadi nih. Yuk, login dan cari teman Travelling anda!")
                setPositiveButton("Lanjut") { _, _ ->
                    finish()
                }
                create()
                show()
            }
        } else {
            AlertDialog.Builder(this).apply {
                setTitle("Yah!")
                setMessage("Akun anda gagal dibuat nih. Silahkan coba lagi nanti..")
                setPositiveButton("Coba lagi") { _, _ ->

                }
                create()
                show()
            }
        }
    }
}