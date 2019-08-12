package com.example.ridecellpracticaldemo.viewmodels

import com.example.ridecellpracticaldemo.fragments.getPhotosListener
import com.example.ridecellpracticaldemo.responsemodels.ListPhotosResponse
import com.example.ridecellpracticaldemo.utils.AppUtility
import com.example.ridecellpracticaldemo.utils.RediCelApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListMainPhotosViewModel : BaseViewModel() {


    fun getPhotos(listener: getPhotosListener) {
        var listenerObject = listener

        AppUtility.showProgress(context)
        val apiService = RediCelApiInterface.create()

        val call = apiService.callListPhotos(
            "6bf318919bbbc455f3573d18798a58e3",
            "flickr.photos.search", AppUtility.searchString, "url_o", "json",
            "1"
        )
        call.enqueue(object : Callback<ListPhotosResponse> {

            override fun onResponse(call: Call<ListPhotosResponse>, response: Response<ListPhotosResponse>) {
                AppUtility.dismissProgress()
                if (response != null && response.body() != null) {
                    listenerObject.getPhotos(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ListPhotosResponse>, t: Throwable) {
                AppUtility.dismissProgress()
            }

        })

    }


}