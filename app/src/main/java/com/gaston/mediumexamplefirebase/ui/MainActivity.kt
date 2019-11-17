package com.gaston.mediumexamplefirebase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
        sendData()
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

    fun showProgress(){
        progressBar.visibility = View.VISIBLE
        btn_enviar.isEnabled = false
    }

    fun hideProgress(){
        progressBar.visibility = View.GONE
        btn_enviar.isEnabled = true
    }

    fun sendData(){
        btn_enviar.setOnClickListener {
            showProgress()
            val email = etxtemail.text.toString().trim()
            val name = etxtname.text.toString().trim()
            val surname = etxtsurname.text.toString().trim()
            val buydollars = etxtbuy.text.toString().toInt()
            if(email.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty() && buydollars >= 0){
                viewModel.sendDataToFirebase(email,name,surname,buydollars).observe(this, Observer {
                    when(it.status){
                        Status.SUCCESS -> {
                            hideProgress()
                            Toast.makeText(this,"Gracias por su compra !",Toast.LENGTH_SHORT).show()
                        }
                        Status.ERROR -> {
                            hideProgress()
                            Toast.makeText(this,"Hubo un problema al procesar su compra: ${it.message}",Toast.LENGTH_SHORT).show()
                        }
                        else -> {Toast.makeText(this,"Hubo un problema, vuelva a intentarlo",Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }
        }

    }
}
