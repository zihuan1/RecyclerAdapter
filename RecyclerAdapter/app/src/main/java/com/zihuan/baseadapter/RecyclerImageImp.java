package com.zihuan.baseadapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author zihuan
 * @Description
 * @date 2019/9/28 12:08
 */
public class RecyclerImageImp implements RecyclerBindImageLoading {

    @Override
    public void displayImage(Context context, ImageView view, String url) {
        Glide.with(MainApplication.mainApplication).load(url).into(view);
    }

    @Override
    public void displayImage(Context context, ImageView imageView, Integer resId) {
        imageView.setImageResource(resId);
    }
}
