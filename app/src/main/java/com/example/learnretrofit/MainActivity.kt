package com.example.learnretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.learnretrofit.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textUserId = findViewById<TextView>(R.id.textUserId)
        val textId = findViewById<TextView>(R.id.textId)
        val textTitle = findViewById<TextView>(R.id.textTitle)
        val textBody = findViewById<TextView>(R.id.textBody)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                textUserId.text = response.body()?.userId.toString()
                textId.text = response.body()?.id.toString()
                textTitle.text = response.body()?.title!!
                textBody.text = response.body()?.body!!
            } else {
                Log.d("Error", response.errorBody().toString())
                textUserId.text = response.code().toString()
            }
        })
    }
}