package com.example.rotah

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import com.example.rotah.databinding.ActivityLevelTigaBinding

class LevelTiga : AppCompatActivity()  , TimerManager.TimerCallback {

    private lateinit var binding: ActivityLevelTigaBinding
    private var booleanJawabanStatus = booleanArrayOf(false, false, false)
    private var firstRun : Boolean = true
    private var mediaPlayer: MediaPlayer? = null
    private var totalTimeInMillis: Long = 30000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelTigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this,R.raw.congrat)

        binding.imageViewGif.visibility = View.INVISIBLE
        binding.imageViewCenter.visibility = View.INVISIBLE
        binding.btnLanjut.visibility = View.INVISIBLE

        Toast.makeText(this@LevelTiga, "Apa Bahasa Arabnya gambar di atas ", Toast.LENGTH_LONG).show()

        binding.harfuShad.setOnClickListener {
            val startX = binding.harfuShad.left
            val startY = binding.harfuShad.top
            val endX = binding.tiga.left
            val endY = binding.tiga.top
            val animator = createTranslationAnimator(binding.harfuShad, startX, startY, endX, endY)
            animator.start()
            booleanJawabanStatus[0] = true
            cekStatusSemuaJawaban()
        }
        binding.harfuMim.setOnClickListener {
            Toast.makeText(this@LevelTiga, "Salah.. ", Toast.LENGTH_SHORT).show()
        }
        binding.harfuBa.setOnClickListener {
            val startX = binding.harfuBa.left
            val startY = binding.harfuBa.top
            val endX = binding.dua.left
            val endY = binding.dua.top
            val animator = createTranslationAnimator(binding.harfuBa, startX, startY, endX, endY)
            animator.start()
            booleanJawabanStatus[1] = true
            cekStatusSemuaJawaban()
       }
        binding.harfuKaf.setOnClickListener {
            Toast.makeText(this@LevelTiga, "Salah.. ", Toast.LENGTH_SHORT).show()
      }
        binding.harfuYa.setOnClickListener {
            val startX = binding.harfuYa.left
            val startY = binding.harfuYa.top
            val endX = binding.satu.left
            val endY = binding.satu.top
            val animator = createTranslationAnimator(binding.harfuYa, startX, startY, endX, endY)
            animator.start()
            booleanJawabanStatus[2] = true
            cekStatusSemuaJawaban()
        }
        binding.btnLanjut.setOnClickListener {
            pindahHalaman(ResultActivity::class.java)
        }
        binding.progressBar.max = totalTimeInMillis.toInt()
        TimerManager.registerCallback(this)
        if (firstRun){
            val intent = getIntent()
            Log.d("LevelTigaAct ", "Hasil waktu dari intent $totalTimeInMillis")
            totalTimeInMillis = intent.getLongExtra("waktu", 0)
            TimerManager.startTimer(totalTimeInMillis)
        }
    }

    private fun cekStatusSemuaJawaban() {
        for (i in booleanJawabanStatus.indices) {
            Log.d("TAG", "booleanArray[$i]: ${booleanJawabanStatus[i]}")
        }
        val allTrue = booleanJawabanStatus.all { it }
        if (allTrue){
            TimerManager.stopTimer()
            Toast.makeText(this@LevelTiga, "Selamat  jawaban Benar ", Toast.LENGTH_SHORT).show()

            mediaPlayer?.start()
            Handler().postDelayed({

            binding.imageViewGif.visibility = View.VISIBLE
            binding.imageViewCenter.visibility = View.VISIBLE
            binding.btnLanjut.visibility = View.VISIBLE
            },500)
        }

    }
    private fun pindahHalaman(tujuan : Class<*>) {
            val intent = Intent(this@LevelTiga, tujuan)
            intent.putExtra("waktu", totalTimeInMillis)

            startActivity(intent)
        }
    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val secondsRemaining = seconds % 60
        return String.format("%02d:%02d", minutes, secondsRemaining)
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
    private fun createTranslationAnimator(
        view: View,
        startX: Int,
        startY: Int,
        endX: Int,
        endY: Int
    ): ObjectAnimator {
        val translationX = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, (endX - startX).toFloat())
        val translationY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, (endY - startY).toFloat())
        return ObjectAnimator.ofPropertyValuesHolder(view, translationX, translationY).apply {
            duration = 1000 // Atur durasi animasi sesuai keinginan Anda
        }
    }
    override fun onPause() {
        super.onPause()
        Log.d("animActivity","masuk onPause")

        //hentikan hitung mundur
        TimerManager.stopTimer()
    }
    override fun onResume() {
        super.onResume()
        Log.d("animActivity","masuk onResume")
    }
    override fun onTimerTick(millisUntilFinished : Long) {
        runOnUiThread {
            totalTimeInMillis =  millisUntilFinished
            val seconds = millisUntilFinished / 1000
            val waktuString = formatTime(seconds)
            binding.textviewTimer.text = "Waktu: $waktuString"
            binding.progressBar.progress = millisUntilFinished.toInt()
        }
        Log.d("animActivity onTick",millisUntilFinished.toString())
    }
    override fun onTimerFinish() {
        binding.progressBar.progress = 0
        pindahHalaman(ResultActivity::class.java)
    }
}