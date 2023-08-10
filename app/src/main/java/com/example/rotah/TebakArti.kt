package com.example.rotah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.rotah.databinding.ActivityTebakArtiBinding

class TebakArti : AppCompatActivity() {

    private lateinit var binding: ActivityTebakArtiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTebakArtiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}