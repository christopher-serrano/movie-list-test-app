package com.serranocjm.movielisttestapp.ui.activity

import android.os.Bundle
import com.serranocjm.movielisttestapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        checkCoarseLocationPermission()
    }
}
