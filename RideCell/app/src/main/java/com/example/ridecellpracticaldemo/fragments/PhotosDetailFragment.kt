package com.example.ridecellpracticaldemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ridecellpracticaldemo.responsemodels.PhotoDetailResponse
import com.example.ridecellpracticaldemo.viewmodels.PhotoDetailViewModel

class PhotosDetailFragment : Fragment() {


    var binding: com.example.ridecellpracticaldemo.databinding.FrgPhotoDetailBinding? = null
    lateinit var viewModelList: PhotoDetailViewModel
    val safeVarargs: PhotosDetailFragmentArgs by navArgs()
    val photUrl by lazy {
        safeVarargs.photoUrl
    }
    val photId by lazy {
        safeVarargs.photoId
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            com.example.ridecellpracticaldemo.databinding.FrgPhotoDetailBinding.inflate(inflater, container, false)
                .also {
                    binding = it
                }.root
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = this
        viewModelList = PhotoDetailViewModel()

        viewModelList.initParams(context!!)

        viewModelList.getPhotosDetail(object : getPhotoDetailListener {
            override fun getePhotDetail(model: PhotoDetailResponse) {
                model.photoUrl=photUrl
                binding!!.model = model

            }
        }, photId)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


}

interface getPhotoDetailListener {
    fun getePhotDetail(model: PhotoDetailResponse)
}
