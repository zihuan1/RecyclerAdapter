package com.zihuan.baseadapter;

import android.content.Context;
import android.widget.ImageView;

import com.zihuan.baseadapter.listener.RecyclerBindImageLoading;

import java.util.regex.Pattern;

/**
 * @author Zihuan
 * @Description
 * @date 2019/9/28 10:55
 */
public class RecyclerAdapterConfig {
    private static RecyclerAdapterConfig mConfig;
    private Pattern mPattern = Pattern.compile("^[-\\+]?[\\d]*$");
    private RecyclerBindImageLoading mBindImageLoading;

    public static RecyclerAdapterConfig getInstance() {
        if (null == mConfig) {
            mConfig = new RecyclerAdapterConfig();
        }
        return mConfig;
    }


    public RecyclerBindImageLoading getBindImageLoading() {
        if (null == mBindImageLoading) {
            return new RecyclerBindImageLoading() {

                @Override
                public void displayImage(Context context, ImageView view, String url) {

                }

                @Override
                public void displayImage(Context context, ImageView imageView, Integer resId) {

                }
            };
        }
        return mBindImageLoading;
    }

    public RecyclerAdapterConfig setBindImageLoading(RecyclerBindImageLoading mBindImageLoading) {
        this.mBindImageLoading = mBindImageLoading;
        return this;
    }

    /**
     * 设置默认的图片Url规则
     *
     * @param pattern
     */
    public void setPatter(Pattern pattern) {
        mPattern = pattern;
    }

    public boolean isInt(String url) {
        return mPattern.matcher(url).matches();
    }

}
