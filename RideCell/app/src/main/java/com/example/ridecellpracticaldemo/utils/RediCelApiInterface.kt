package com.example.ridecellpracticaldemo.utils

import com.example.ridecellpracticaldemo.responsemodels.ListPhotosResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RediCelApiInterface {

    @GET("services/rest")
    fun callListPhotos(
        @Query("api_key") apiKey: String,
        @Query("method") method: String,
        @Query("tags") tags: String,
        @Query("extras") extras: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String
    ): Call<ListPhotosResponse>

    @GET("services/rest")
    fun callDetailPhotos(
        @Query("api_key") apiKey: String,
        @Query("method") method: String,
        @Query("photo_id") photo_id:String,
        @Query("extras") extras: String,
        @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: String
    ): Call<ResponseBody>


    companion object Factory {
        val BASE_URL = "https://api.flickr.com/"
        fun create(): RediCelApiInterface {
            val builder = OkHttpClient.Builder()
            val client = builder.build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RediCelApiInterface::class.java)
        }
    }


}