package com.adapthink.flicktestapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.adapthink.flicktestapp.databinding.ActivitySplashScreenBinding
import com.adapthink.flicktestapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val count = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }
        }
        count.start()
    }
}