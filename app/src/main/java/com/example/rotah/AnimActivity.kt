package com.example.rotah

import TimerManager
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ProgressBar
import android.widget.Toast
import com.example.rotah.databinding.ActivityAnimBinding

class AnimActivity : AppCompatActivity() , TimerManager.TimerCallback {
    private lateinit var binding :  ActivityAnimBinding
    private val booleanJawabanStatus = booleanArrayOf(false, false, false, false)
    private var mediaPlayer: MediaPlayer? = null
    private var timeString = ""

    private lateinit var countdownTimer: CountDownTimer
    private var firstRun : Boolean = true
    private var totalTimeInMillis: Long = 30000 // Total waktu dalam milidetik (60 detik)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        mediaPlayer = MediaPlayer.create(this, R.raw.congrat)


        binding.imageViewGif.visibility = View.INVISIBLE
        binding.imageViewCenter.visibility = View.INVISIBLE
        binding.btnLanjut.visibility = View.INVISIBLE

        Toast.makeText(this@AnimActivity, "Apa Bahasa Arab benda di atas ", Toast.LENGTH_LONG).show()




        binding.harfuAlif.setOnClickListener {
            // Ambil posisi X dan Y saat ini dari ImageView
            val startX = binding.harfuAlif.left
            val startY = binding.harfuAlif.top
            // Ambil posisi X dan Y dari Button
            val endX = binding.dua.left
            val endY = binding.dua.top
//            Animate(binding.harfuAlif, startX, startY, endX, endY)
            val animator = createTranslationAnimator(binding.harfuAlif, startX, startY, endX, endY)
            animator.start()
            booleanJawabanStatus[0] = true
            cekStatusSemuaJawaban()
        }
        binding.harfuTa.setOnClickListener {

            val startX = binding.harfuTa.left
            val startY = binding.harfuTa.top
            val endX = binding.tiga.left
            val endY = binding.tiga.top
//            Animate(binding.harfuTa, startX, startY, endX, endY)

            val animator = createTranslationAnimator(binding.harfuTa, startX, startY, endX, endY)
            animator.start()
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
//            Animate(binding.harfuKaf, startX, startY, endX, endY)
            val animator = createTranslationAnimator(binding.harfuKaf, startX, startY, endX, endY)
            animator.start()

              booleanJawabanStatus[2] = true
              cekStatusSemuaJawaban()

        }
        binding.harfuBa.setOnClickListener {
            val startX = binding.harfuBa.left
            val startY = binding.harfuBa.top
            val endX = binding.satu.left
            val endY = binding.satu.top
//            Animate(binding.harfuBa, startX, startY, endX, endY)
            val animator = createTranslationAnimator(binding.harfuBa, startX, startY, endX, endY)
            animator.start()
              booleanJawabanStatus[3] = true
              cekStatusSemuaJawaban()
        }
        binding.harfuQaf.setOnClickListener {



              Toast.makeText(this@AnimActivity, "Salah.. ", Toast.LENGTH_SHORT).show()
              Toast.makeText(this@AnimActivity, "clue : pilihlah huruf untuk 'kitab'", Toast.LENGTH_SHORT).show()
        }
        binding.btnLanjut.setOnClickListener {
           pindahHalaman(LevelDua::class.java)
        }
        binding.progressBar.max = totalTimeInMillis.toInt()
        TimerManager.registerCallback(this)

        if (firstRun){
            TimerManager.startTimer(totalTimeInMillis)
            firstRun = false

        }
    }



    private fun pindahHalaman(tujuan : Class<*>) {


        val animIn = R.anim.slide_in_right
        val animOut = R.anim.slide_out_left

        val arah = Intent(this, tujuan)
        arah.putExtra("waktu", totalTimeInMillis)
        startActivity(arah)
        finish()
        overridePendingTransition(animIn, animOut)



    }

    private fun cekStatusSemuaJawaban() {
        for (i in booleanJawabanStatus.indices) {
            Log.d("TAG", "booleanArray[$i]: ${booleanJawabanStatus[i]}")
        }
        val allTrue = booleanJawabanStatus.all { it }
        if (allTrue){
            TimerManager.stopTimer()
            mediaPlayer?.start()
            Handler().postDelayed({
            Toast.makeText(this@AnimActivity, "Selamat  jawaban Benar ", Toast.LENGTH_SHORT).show()
            binding.imageViewGif.visibility = View.VISIBLE
            binding.imageViewCenter.visibility = View.VISIBLE
            binding.btnLanjut.visibility = View.VISIBLE

            }, 500)
        }
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