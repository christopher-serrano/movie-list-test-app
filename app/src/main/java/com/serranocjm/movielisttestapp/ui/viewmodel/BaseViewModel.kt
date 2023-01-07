package com.serranocjm.movielisttestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val loadingState = MutableLiveData<Boolean>()
    val onSuccess = MutableLiveData<Any>()
    val onError = MutableLiveData<String>()

    protected fun showLoading() = loadingState.postValue(true)
    protected fun dismissLoading() = loadingState.postValue(false)
}
