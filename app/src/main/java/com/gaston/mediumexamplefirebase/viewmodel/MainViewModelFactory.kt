package com.gaston.mediumexamplefirebase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaston.mediumexamplefirebase.usecase.UserUseCase

/**
 * Created by Gastón Saillén on 15 November 2019
 */
class MainViewModelFactory(val userUseCase: UserUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserUseCase::class.java).newInstance(userUseCase)
    }
}