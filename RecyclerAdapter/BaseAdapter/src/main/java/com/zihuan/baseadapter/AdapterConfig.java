package com.zihuan.baseadapter;

import android.widget.ImageView;

/**
 * Demo class
 *
 * @author zihuan
 * @Description
 * @date 2019/9/28 10:55
 */
public class AdapterConfig {
    private static AdapterConfig mConfig;

    public static AdapterConfig getInstance() {
        if (null == mConfig) {
            mConfig = new AdapterConfig();
        }
        return mConfig;
    }

    private RecyclerBindImageLoading mBindImageLoading;

    public RecyclerBindImageLoading getBindImageLoading() {
        if (null == mBindImageLoading) {
            return new RecyclerBindImageLoading() {
                @Override
                public void loadImage(ImageView view, String url) {

                }
            };
        }
        return mBindImageLoading;
    }

    public AdapterConfig setBindImageLoading(RecyclerBindImageLoading mBindImageLoading) {
        this.mBindImageLoading = mBindImageLoading;
        return this;
    }
}
