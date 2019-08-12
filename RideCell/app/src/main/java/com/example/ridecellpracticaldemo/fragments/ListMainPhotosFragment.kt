package com.example.ridecellpracticaldemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ridecellpracticaldemo.BR
import com.example.ridecellpracticaldemo.R
import com.example.ridecellpracticaldemo.databinding.ItemPhotosMainBinding
import com.example.ridecellpracticaldemo.databinding.ItemPhotosMainHorizontalBinding
import com.example.ridecellpracticaldemo.responsemodels.ListPhotosResponse
import com.example.ridecellpracticaldemo.responsemodels.Photo
import com.example.ridecellpracticaldemo.utils.genericadapter.LastAdapter
import com.example.ridecellpracticaldemo.utils.genericadapter.Type
import com.example.ridecellpracticaldemo.viewmodels.ListMainPhotosViewModel


class ListMainPhotosFragment : Fragment() {

    var binding: com.example.ridecellpracticaldemo.databinding.FrgMainImageListBinding? = null
    lateinit var viewModelList: ListMainPhotosViewModel

    var arraysPhotos = ArrayList<Photo>()
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            com.example.ridecellpracticaldemo.databinding.FrgMainImageListBinding.inflate(inflater, container, false)
                .also {
                    binding = it
                }.root
        }
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = this
        viewModelList = ListMainPhotosViewModel()

        viewModelList.initParams(context!!)
        binding?.let {

            it.btnSearchText.setOnClickListener {
                NavHostFragment.findNavController(this@ListMainPhotosFragment).navigate(R.id.actionSearchFragment)
            }
            var layoutmanager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            it.rclMainListPhotos.layoutManager = layoutmanager
            var adapterVertical = LastAdapter(arraysPhotos, BR.model)
                .map<Photo>(
                    Type<ItemPhotosMainBinding>(R.layout.item_photos_main)
                        .onClick {
                            var url = ""
                            if (!(it.binding.model!!.urlO.isNullOrEmpty())) {
                                url = it.binding.model!!.urlO
                            }
                            val action =
                                ListMainPhotosFragmentDirections.actionPhotoDetailFragment(it.binding.model!!.id!!, url)
                            NavHostFragment.findNavController(this@ListMainPhotosFragment).navigate(action)
                        }
                )

            var adapterHorizontal = LastAdapter(arraysPhotos, BR.model)
                .map<Photo>(
                    Type<ItemPhotosMainHorizontalBinding>(R.layout.item_photos_main_horizontal)
                        .onClick {

                            var url = ""
                            if (!(it.binding.model!!.urlO.isNullOrEmpty())) {
                                url = it.binding.model!!.urlO
                            }
                            val action =
                                ListMainPhotosFragmentDirections.actionPhotoDetailFragment(it.binding.model!!.id!!, url)
                            NavHostFragment.findNavController(this@ListMainPhotosFragment).navigate(action)
                        }
                )

            it.rclMainListPhotos.adapter = adapterVertical

            it.tgView.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {

                    if (isChecked) {
                        it.rclMainListPhotos.setOnFlingListener(null)
                        val helper = LinearSnapHelper()
                        helper.attachToRecyclerView(it.rclMainListPhotos)
                        var layoutmanagerVertical = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        it.rclMainListPhotos.layoutManager = layoutmanagerVertical
                        val itemDecor = DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
                        it.rclMainListPhotos.addItemDecoration(itemDecor)
                        it.rclMainListPhotos.adapter = adapterHorizontal

                    } else {
                        it.rclMainListPhotos.layoutManager = layoutmanager
                        val itemDecor = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
                        it.rclMainListPhotos.addItemDecoration(itemDecor)
                        it.rclMainListPhotos.adapter = adapterVertical
                    }
                }
            })

        }


        viewModelList.getPhotos(object : getPhotosListener {
            override fun getPhotos(model: ListPhotosResponse) {
                arraysPhotos.clear()


                arraysPhotos.addAll(model.photos.photo)

                binding!!.rclMainListPhotos.adapter!!.notifyDataSetChanged()

            }
        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }


}

interface getPhotosListener {
    fun getPhotos(model: ListPhotosResponse)
}
