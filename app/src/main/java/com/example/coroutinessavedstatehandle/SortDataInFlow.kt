package com.example.coroutinessavedstatehandle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

import kotlinx.coroutines.flow.transform

suspend fun <T> Flow<T>.sortDataInFlow():Flow<T>{
    val list = this.toList()
    val newList = list.reversed()

    return newList.asFlow()
}