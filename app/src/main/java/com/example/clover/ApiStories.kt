package com.example.clover

import com.example.clover.model.MyDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiStories {

@GET("/v2/merchant/name?access_token=fd011f23-6b0c-cf10-9c6b-450b306c22da")
fun getResponse() :Call<MyDataItem>
}