package com.assignment.synaos.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
open class BaseViewModel<K>():ViewModel() {
     val stateLiveData = MutableLiveData<K>()

}