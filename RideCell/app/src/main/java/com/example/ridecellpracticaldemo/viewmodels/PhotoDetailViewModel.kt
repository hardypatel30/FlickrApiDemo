package com.example.ridecellpracticaldemo.viewmodels

import com.example.ridecellpracticaldemo.fragments.getPhotoDetailListener
import com.example.ridecellpracticaldemo.responsemodels.PhotoDetailResponse

import com.example.ridecellpracticaldemo.utils.AppUtility
import com.example.ridecellpracticaldemo.utils.RediCelApiInterface
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoDetailViewModel : BaseViewModel() {


    fun getPhotosDetail(listener: getPhotoDetailListener, photo_id: String) {
        var listenerObject = listener

        AppUtility.showProgress(context)
        val apiService = RediCelApiInterface.create()

        val call = apiService.callDetailPhotos(
            "6bf318919bbbc455f3573d18798a58e3",
            "flickr.photos.getInfo", photo_id, "url_o", "json",
            "1"
        )
        call.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                AppUtility.dismissProgress()
                if (response != null && response.body() != null) {
                    var model = PhotoDetailResponse()
                    var str_response = response.body()!!.string()
                    var responseJSON = JSONObject(str_response)
                    var photoObject = responseJSON.getJSONObject("photo")
                    var ownerObject = photoObject.getJSONObject("owner")
                    model.autherName = ownerObject.getString("username")
                    model.title = ownerObject.getString("location")

                    listenerObject.getePhotDetail(model)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                AppUtility.dismissProgress()
            }

        })

    }
}