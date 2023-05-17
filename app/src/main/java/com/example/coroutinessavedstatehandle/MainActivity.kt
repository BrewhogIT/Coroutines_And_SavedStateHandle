package com.example.coroutinessavedstatehandle

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.activity.viewModels
import com.example.coroutinessavedstatehandle.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

      CoroutineScope(Dispatchers.IO).launch {
          val list = listOf(1,2,3)
          val flow: Flow<Int> =list.asFlow()

          flow.sortDataInFlow().collect {
             Log.d("Flow", it.toString())
          }
      }

    }


}