package com.gaston.mediumexamplefirebase.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gaston.mediumexamplefirebase.vo.Resource
import com.google.firebase.firestore.FirebaseFirestore

/**
 * Created by Gastón Saillén on 15 November 2019
 */
class UserDao {

    fun getUserName(userId:String): LiveData<Resource<String>> {
        var userName = MutableLiveData<Resource<String>>()
        FirebaseFirestore.getInstance()
            .collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener {
                userName.value = Resource.success(it.getString("name"))

        }.addOnFailureListener {
                userName.value = Resource.error("Error",it.message!!)
        }

        return userName
    }
}