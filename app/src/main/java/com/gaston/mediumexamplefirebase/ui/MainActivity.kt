package com.gaston.mediumexamplefirebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gaston.mediumexamplefirebase.R
import com.gaston.mediumexamplefirebase.usecase.UserUseCase
import com.gaston.mediumexamplefirebase.viewmodel.MainViewModel
import com.gaston.mediumexamplefirebase.viewmodel.MainViewModelFactory
import com.gaston.mediumexamplefirebase.vo.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this,MainViewModelFactory(UserUseCase())).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeForUserDetails("Gaston")
    }

    fun observeForUserDetails(userId: String) {
        viewModel.retrieveUserData(userId).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> txtView.text = it.data
                Status.ERROR -> txtView.text = "Error trying to retrieve the data: ${it.data}"
                else -> txtView.text = "Hello world :)"

            }
        })
    }
}
