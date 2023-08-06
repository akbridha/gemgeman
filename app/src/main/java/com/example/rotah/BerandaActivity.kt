package com.example.rotah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rotah.databinding.ActivityBerandaBinding

class BerandaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBerandaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBerandaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.button.setOnClickListener {
            pindahHalaman(AnimActivity::class.java)
        }



    }

    private fun pindahHalaman(tujuan : Class<*>) {


        val animIn = R.anim.slide_in_right
        val animOut = R.anim.slide_out_left

        val arah = Intent(this, tujuan)

        startActivity(arah)
        finish()
        overridePendingTransition(animIn, animOut)
    }
}