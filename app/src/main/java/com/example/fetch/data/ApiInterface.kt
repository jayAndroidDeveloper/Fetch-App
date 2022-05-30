package com.example.fetch.data

import com.example.fetch.Model.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("projects")
    fun getProjects(): Call<List<DataResponse>>
}