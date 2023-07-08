package com.example.rotah

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rotah.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val SPLASH_DELAY: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()





        Handler().postDelayed({
            val fadeInAnimation = AlphaAnimation(0f, 1f) // Membuat efek fade-in dari transparansi 0 hingga 1
            fadeInAnimation.duration = 1000 // Durasi animasi fade-in dalam milidetik (1 detik)

            binding.fadeInTextView.startAnimation(fadeInAnimation)

            // Mengatur animasi dengan TranslateAnimation
            val animation = TranslateAnimation(0f, 1000f, 0f, 0f) // Menggeser dari kiri ke kanan sejauh 1000 piksel
            animation.duration = 3000 // Durasi animasi dalam milidetik (3 detik)
            animation.repeatCount = Animation.INFINITE // Mengulang animasi secara terus-menerus
            animation.repeatMode = Animation.REVERSE // Membalik arah animasi setelah mencapai titik akhir

            binding.textView.startAnimation(animation)

            // Mengatur penundaan dan memulai Activity baru setelah 3 detik
            Handler().postDelayed({
                val intent = Intent(this, AnimActivity::class.java)
                startActivity(intent)
                finish() // Menutup Splash Activity agar tidak bisa kembali dengan tombol "Back"
            }, SPLASH_DELAY)
        }, 500)

    }
}