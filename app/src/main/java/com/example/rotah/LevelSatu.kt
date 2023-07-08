package com.example.rotah

import android.animation.Animator
import android.animation.AnimatorInflater
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rotah.databinding.ActivityLevelSatuBinding

class LevelSatu : AppCompatActivity() {

    private lateinit var binding: ActivityLevelSatuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLevelSatuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Kanan.setOnClickListener {
            startActivity(Intent(this@LevelSatu, AnimActivity::class.java))
        }


        // Mendapatkan referensi elemen dengan menggunakan binding

        val animator = AnimatorInflater.loadAnimator(this, R.animator.move_down)

        // Setel onClickListener untuk elemen
        binding.kiri.setOnClickListener {
//            val screenHeight = resources.displayMetrics.heightPixels
//            val elementHeight = myElement.height
//
//            // Setel posisi elemen ke titik bawah
//            myElement.y = (screenHeight - elementHeight).toFloat()

            animator.setTarget(binding.kiri)
//            animator.start()

        }
        animator.addListener(
            object : Animator.AnimatorListener{
            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onAnimationStart(animation: Animator) {
                TODO("Not yet implemented")
            }

            override fun onAnimationEnd(animation: Animator) {
                TODO("Not yet implemented")
            }

            override fun onAnimationCancel(animation: Animator) {
                TODO("Not yet implemented")
            }

            override fun onAnimationRepeat(animation: Animator) {
                TODO("Not yet implemented")
            }
        }
        )






    }

}