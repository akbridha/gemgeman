package com.example.rotah

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.rotah.databinding.ActivityAnimBinding
import com.example.rotah.databinding.ActivityLevelDuaBinding

class LevelDua : AppCompatActivity() , TimerManager.TimerCallback {

    private lateinit var binding: ActivityLevelDuaBinding
    private var booleanJawabanStatus = booleanArrayOf(false, false, false, false)
    private var mediaPlayer: MediaPlayer? = null
    private var timeString = ""


    override fun onCreate(savedInstanceState: Bundle?) {
      binding = ActivityLevelDuaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(this, R.raw.congrat)


        binding.imageViewGif.visibility = View.INVISIBLE
        binding.imageViewCenter.visibility = View.INVISIBLE
        binding.btnLanjut.visibility = View.INVISIBLE



    }






    override fun onTimerTick(millisUntilFinished : Long) {
        runOnUiThread {


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

    private fun pindahHalaman(tujuan : Class<*>) {
        val intent = Intent(this@LevelDua, tujuan)
        intent.putExtra("waktu", timeString)

        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
        Log.d("LevelDuaActivity","masuk onPause")

        //hentikan hitung mundur

        TimerManager.stopTimer()

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


    private fun cekStatusSemuaJawaban() {
        for (i in booleanJawabanStatus.indices) {
            Log.d("TAG", "booleanArray[$i]: ${booleanJawabanStatus[i]}")
        }
        val allTrue = booleanJawabanStatus.all { it }
        if (allTrue){

            mediaPlayer?.start()
            Toast.makeText(this@LevelDua, "Selamat  jawaban Benar ", Toast.LENGTH_SHORT).show()
            binding.imageViewGif.visibility = View.VISIBLE
            binding.imageViewCenter.visibility = View.VISIBLE
            binding.btnLanjut.visibility = View.VISIBLE
        }
    }


    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val secondsRemaining = seconds % 60
        return String.format("%02d:%02d", minutes, secondsRemaining)
    }

}