package com.example.rotah

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.rotah.databinding.ActivityAnimBinding
import com.example.rotah.databinding.ActivityLevelSatuBinding

class AnimActivity : AppCompatActivity() {
    private lateinit var binding :  ActivityAnimBinding
    private var isSatuMoved = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Atur listener klik pada button
        binding.harfuBa.setOnClickListener {
            // Ambil posisi X dan Y saat ini dari ImageView

            val startX = binding.harfuBa.left
            val startY = binding.harfuBa.top

            // Ambil posisi X dan Y dari Button
            val endX = binding.satu.left
            val endY = binding.satu.top
            Animate(binding.harfuBa, startX, startY, endX, endY)


        }

    }

    private fun Animate(view: View, startX: Int, startY: Int, endX: Int, endY: Int) {
        // Buat animasi TranslateAnimation dari posisi saat ini ke posisi tujuan
        val animation = TranslateAnimation(0f, (endX - startX).toFloat(), 0f, (endY - startY).toFloat())
        animation.duration = 1000 // Atur durasi animasi sesuai keinginan Anda

        // Atur listener untuk menghapus animasi setelah selesai
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // Kosongkan, tidak ada tindakan khusus yang diperlukan saat animasi dimulai
            }

            override fun onAnimationEnd(animation: Animation) {
                // Atur posisi ImageView ke posisi tujuan setelah animasi selesai
                view.layout(
                    endX,
                    endY,
                    endX + view.width,
                    endY + view.height
                )
            }

            override fun onAnimationRepeat(animation: Animation) {
                // Kosongkan, tidak ada tindakan khusus yang diperlukan saat animasi diulang
            }
        })

        // Mulai animasi pada ImageView
        view.startAnimation(animation)
    }
}