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
//                val intent = Intent(this, AnimActivity::class.java)
//                startActivity(intent)

                val animIn = R.anim.slide_in_up
                val animOut = R.anim.slide_out_down
                val arah = Intent(this, AnimActivity::class.java)
                startActivity(arah)
                finish()
                overridePendingTransition(animIn, animOut)



            }, SPLASH_DELAY)
        }, 500)

    }
    private fun pindahActivity() {
//        val animIn = when (direksi) {
//            "register" -> R.anim.slide_in_up
//            "beranda" -> R.anim.slide_in_down
//            else -> R.anim.slide_in_left // Nilai default jika arah tidak valid
//        }
//        val animOut = when (direksi) {
//            "register" -> R.anim.slide_out_down
//            "beranda" -> R.anim.slide_out_up
//            else -> R.anim.slide_out_right // Nilai default jika arah tidak valid
//        }
//        val intent = when (direksi) {
//            "register" -> Intent(this, RegisterActivity::class.java)
//            "beranda" -> Intent(this, AnimActivity::class.java)
//            else -> Intent(this, MainActivity()::class.java) // Activity default jika arah tidak valid
//        }

        val animIn = R.anim.slide_in_down
        val animOut = R.anim.slide_out_up
        Intent(this, AnimActivity::class.java) // Activity default jika arah tidak valid
        startActivity(intent)
        finish()
        overridePendingTransition(animIn, animOut)
    }
}