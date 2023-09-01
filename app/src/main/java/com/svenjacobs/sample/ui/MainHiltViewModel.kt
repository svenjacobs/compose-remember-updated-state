package com.svenjacobs.sample.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainHiltViewModel @Inject constructor(): ViewModel() {

    fun someCallback() {
    }
}