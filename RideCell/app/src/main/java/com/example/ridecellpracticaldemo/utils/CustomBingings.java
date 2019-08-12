package com.example.ridecellpracticaldemo.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.ridecellpracticaldemo.R;

public class CustomBingings {

    @BindingAdapter("mainImage")
    public static void loadImage(ImageView view, String imageUrl) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.transforms(new CenterCrop(), new RoundedCorners(100));
        requestOptions.placeholder(R.mipmap.default_image);
        requestOptions.error(R.mipmap.default_image);

        Glide.with(view.getContext())

                .load(imageUrl)

                .apply(requestOptions)
                .into(view);
    }

    @BindingAdapter("detailImage")
    public static void loadImageDetail(ImageView view, String imageUrl) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.default_image);
        requestOptions.error(R.mipmap.default_image);

        Glide.with(view.getContext())
                .load(imageUrl)
                .apply(requestOptions
                )
                .into(view);
    }
}
