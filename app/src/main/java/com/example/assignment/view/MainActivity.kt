package com.example.assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment.R
import com.example.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}