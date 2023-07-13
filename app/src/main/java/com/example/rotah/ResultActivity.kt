package com.example.rotah

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.rotah.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private var statusResult = ""



    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)

        setContentView(binding.root)

      val intent = getIntent()
        statusResult = intent.getStringExtra("waktu").toString()


        binding.textViewResult.setText(statusResult)
    }


}