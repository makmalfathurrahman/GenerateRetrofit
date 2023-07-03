package com.example.learnretrofit.repository

import com.example.learnretrofit.api.RetrofitInstance
import com.example.learnretrofit.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

}