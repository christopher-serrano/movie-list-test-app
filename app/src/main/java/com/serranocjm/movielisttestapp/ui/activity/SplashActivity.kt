package com.serranocjm.movielisttestapp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.serranocjm.movielisttestapp.databinding.ActivitySplashBinding
import com.serranocjm.movielisttestapp.utils.delayAction

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        gotoMain()
    }

    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        delayAction(2000L) {
            startActivity(intent)
            this.finish()
        }
    }
}
