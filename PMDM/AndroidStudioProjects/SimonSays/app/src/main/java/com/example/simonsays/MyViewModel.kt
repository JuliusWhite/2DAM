package com.example.simonsays

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    // to print data in the logcat easily
    val TAG_LOG: String = "ViewModel message"

    // sequence list
    val seq = mutableListOf<Int>()

    // instantiation of a MutableLiveData to observe the MutableList<Int> updates
    val livedata_seq = MutableLiveData<MutableList<Int>>()

    // initialization of variables
    init {
        Log.d(TAG_LOG, "Livedata initialization")
    }

    // adds one color to the sequence
    fun addStep() {
        Log.d("State", "Adding one step to the sequence")

        // adding color to the sequence
        val num = (0..3).random()
        seq.add(num)

        // updating the livedata
        livedata_seq.value = seq
    }

}