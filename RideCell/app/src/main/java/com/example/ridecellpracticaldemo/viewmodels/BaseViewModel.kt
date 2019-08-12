package com.example.ridecellpracticaldemo.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {

    lateinit var context: Context


    fun initParams(con: Context) {
        context = con

    }

}