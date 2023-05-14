package com.example.coroutinessavedstatehandle

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.activity.viewModels
import com.example.coroutinessavedstatehandle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this){
            binding.textView.text = it.toString()
        }

        binding.btnStart.setOnClickListener {
            viewModel.start()

        }

        binding.btnStop.setOnClickListener {
            viewModel.stop()
        }

    }


}