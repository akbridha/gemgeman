package com.example.rotah

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.rotah.databinding.ActivityAnimBinding
import com.example.rotah.databinding.ActivityLevelSatuBinding

class AnimActivity : AppCompatActivity() {
    private lateinit var binding :  ActivityAnimBinding
    private val booleanJawabanStatus = booleanArrayOf(false, false, false, false)
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(this, R.raw.congrat)


        binding.imageViewGif.visibility = View.INVISIBLE
        binding.imageViewCenter.visibility = View.INVISIBLE

        Toast.makeText(this@AnimActivity, "Apa Bahasa Arab benda yang dipegang anak tersebut..? ", Toast.LENGTH_LONG).show()

        binding.harfuAlif.setOnClickListener {
            // Ambil posisi X dan Y saat ini dari ImageView
            val startX = binding.harfuAlif.left
            val startY = binding.harfuAlif.top
            // Ambil posisi X dan Y dari Button
            val endX = binding.dua.left
            val endY = binding.dua.top
            Animate(binding.harfuAlif, startX, startY, endX, endY)
            booleanJawabanStatus[0] = true
            cekStatusSemuaJawaban()
        }
        binding.harfuTa.setOnClickListener {

            // Ambil posisi X dan Y saat ini dari ImageView
            val startX = binding.harfuTa.left
            val startY = binding.harfuTa.top
            // Ambil posisi X dan Y dari Button
            val endX = binding.tiga.left
            val endY = binding.tiga.top
            Animate(binding.harfuTa, startX, startY, endX, endY)

            booleanJawabanStatus[1] = true
            cekStatusSemuaJawaban()
        }
        binding.harfuTho.setOnClickListener {

              Toast.makeText(this@AnimActivity, "Salah.. clue : pilihlah huruf untuk 'kitab'", Toast.LENGTH_SHORT).show()
        }
        binding.harfuKaf.setOnClickListener {
            // Ambil posisi X dan Y saat ini dari ImageView
            val startX = binding.harfuKaf.left
            val startY = binding.harfuKaf.top
            // Ambil posisi X dan Y dari Button
            val endX = binding.empat.left
            val endY = binding.empat.top
            Animate(binding.harfuKaf, startX, startY, endX, endY)
              booleanJawabanStatus[2] = true
              cekStatusSemuaJawaban()
        }
        binding.harfuBa.setOnClickListener {
            // Ambil posisi X dan Y saat ini dari ImageView
            val startX = binding.harfuBa.left
            val startY = binding.harfuBa.top
            // Ambil posisi X dan Y dari Button
            val endX = binding.satu.left
            val endY = binding.satu.top
            Animate(binding.harfuBa, startX, startY, endX, endY)
              booleanJawabanStatus[3] = true
              cekStatusSemuaJawaban()
        }
        binding.harfuQaf.setOnClickListener {



              Toast.makeText(this@AnimActivity, "Salah.. ", Toast.LENGTH_SHORT).show()
              Toast.makeText(this@AnimActivity, "clue : pilihlah huruf untuk 'kitab'", Toast.LENGTH_SHORT).show()
        }

    }

    private fun cekStatusSemuaJawaban() {
        for (i in booleanJawabanStatus.indices) {
            Log.d("TAG", "booleanArray[$i]: ${booleanJawabanStatus[i]}")
        }
        val allTrue = booleanJawabanStatus.all { it }
        if (allTrue){

            mediaPlayer?.start()
            Toast.makeText(this@AnimActivity, "Selamat  jawaban Benar ", Toast.LENGTH_SHORT).show()
            binding.imageViewGif.visibility = View.VISIBLE
            binding.imageViewCenter.visibility = View.VISIBLE
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