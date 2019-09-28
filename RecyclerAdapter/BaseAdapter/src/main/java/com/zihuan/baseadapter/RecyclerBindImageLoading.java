package com.zihuan.baseadapter;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Demo class
 *
 * @author zihuan
 * @Description
 * @date 2019/9/28 10:53
 */
public interface RecyclerBindImageLoading {
    void displayImage(Context context,ImageView view, String url);

    void displayImage(Context context,ImageView imageView, @DrawableRes Integer resId);

}
