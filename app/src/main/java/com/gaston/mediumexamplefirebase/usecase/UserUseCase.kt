package com.gaston.mediumexamplefirebase.usecase

import androidx.lifecycle.LiveData
import com.gaston.mediumexamplefirebase.domain.UserDao
import com.gaston.mediumexamplefirebase.vo.Resource

/**
 * Created by Gastón Saillén on 15 November 2019
 */
class UserUseCase {

    private val userDao = UserDao()
    fun retrieveUserData(userId:String): LiveData<Resource<String>> {
        return userDao.getUserName(userId)
    }
}