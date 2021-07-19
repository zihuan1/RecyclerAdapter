package com.zihuan.baseadapter.listener;

import android.view.View;

/**
 * Item点击事件接口
 *
 * @author zihuan
 */
public interface ViewOnItemClick {
    /**
     * item点击事件回调
     *
     * @param view
     * @param position
     */
    void setOnItemClickListener(View view, int position);
}

