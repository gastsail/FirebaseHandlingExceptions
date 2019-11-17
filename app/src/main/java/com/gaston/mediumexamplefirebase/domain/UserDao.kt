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
        val userName = MutableLiveData<Resource<String>>()
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

    fun postEventData(email:String, name:String, surname:String, dollarqty:Int):LiveData<Resource<Boolean>>{
        val datastatus = MutableLiveData<Resource<Boolean>>()
        val userData = User(email,name,surname,dollarqty)
        FirebaseFirestore.getInstance()
            .collection("purchases")
            .add(userData)
            .addOnCompleteListener {
                if(it.isSuccessful) datastatus.value = Resource.success(true)
            }.addOnFailureListener {
                datastatus.value = Resource.error(it.message!!,false)
            }
        return datastatus
    }
}