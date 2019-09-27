package com.zihuan.baseadapter;

import android.view.View;

/**
 * Item长按点击事件接口
 *
 * @author zihuan
 */
public interface ViewOnItemLongClick {
    /**
     * Item长按点击事件回调
     *
     * @param view
     * @param position
     */
    void setOnItemLongClickListener(View view, int position);
}

