package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.View
import com.example.bmi.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            var beratbadan : Float = binding.edtBb.text.toString().toFloat()
            var tinggiBadan : Float = binding.edtTb.text.toString().toFloat()/100

            var index : Float = beratbadan/(tinggiBadan*tinggiBadan)
            binding.tvIndex.text = "index: $index"
        }
    }
}