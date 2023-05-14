package com.example.coroutinessavedstatehandle

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(savedStateHandle: SavedStateHandle):ViewModel() {
    val liveData = savedStateHandle.getLiveData<Int>("key",0)
    lateinit var job: Job
    lateinit var deferred: Deferred<String>

    fun start(){
        //startWithViewModelScope()
        //startWithLaunchCoroutineScope()
        //startWithAsyncCoroutineScope()
        //startWithGlobalScope()
        startWithRunBlocking()


    }

    fun stop(){
        //stopJob()
        stopDeferred()
    }

    private fun startWithViewModelScope(){
        job = viewModelScope.launch {
            loop()
        }
    }

    private fun startWithGlobalScope(){
        job = GlobalScope.launch(Dispatchers.Main) {
            loop()
        }
    }

    private fun startWithLaunchCoroutineScope(){
        job = CoroutineScope(Dispatchers.Main).launch {
            loop()
        }
    }

    private fun startWithAsyncCoroutineScope(){
        deferred = CoroutineScope(Dispatchers.Main).async {
            loop()
            val complete = "Complete!"
            complete
        }
    }

    private fun startWithRunBlocking(){

        runBlocking {

            launch(Dispatchers.IO) {

            }

            async(Dispatchers.Main) {
                

            }


        }

    }

    private fun stopDeferred(){
        CoroutineScope(Dispatchers.IO).launch {
            deferred.cancelAndJoin()
        }
    }

    private fun stopJob(){
        CoroutineScope(Dispatchers.IO).launch{
            job.cancelAndJoin()
        }
    }

    private suspend fun loop(){
        for (i in 0 .. 10){
            delay(1000)
            liveData.value = i
        }
    }
}