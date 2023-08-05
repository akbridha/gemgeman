package com.example.rotah

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rotah.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private var statusResult : Int = 0
    private var mediaPlayer : MediaPlayer? = null


    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

      val intent = getIntent()
        statusResult = (intent.getLongExtra("waktu",0)/1000).toInt()
        Log.d("ResultAct ", "Hasil waktu dari intent $statusResult")

        if (statusResult == 0) {
            mediaPlayer = MediaPlayer.create(this,R.raw.lose)
            Log.d("result", "status result is 0")
            binding.textViewResult.setText("waktu Habis")
            binding.imageSuccess.visibility = View.INVISIBLE
        }else{

            mediaPlayer = MediaPlayer.create(this,R.raw.clap)
            Log.d("result", "status result is not 0")
            binding.textViewResult.setText("Selamat... anda berhasil menyelesaikan kuis")

        }

            mediaPlayer?.start()

    }


}