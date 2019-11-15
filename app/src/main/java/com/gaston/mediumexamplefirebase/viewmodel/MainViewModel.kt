package com.gaston.mediumexamplefirebase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaston.mediumexamplefirebase.usecase.UserUseCase
import com.gaston.mediumexamplefirebase.vo.Resource

/**
 * Created by Gastón Saillén on 15 November 2019
 */
class MainViewModel(private val useCase: UserUseCase) : ViewModel() {

    fun retrieveUserData(userId:String):LiveData<Resource<String>> {
        val userData = MutableLiveData<Resource<String>>()
        useCase.retrieveUserData(userId).observeForever {
            userData.postValue(it)
        }
        return userData
    }
}