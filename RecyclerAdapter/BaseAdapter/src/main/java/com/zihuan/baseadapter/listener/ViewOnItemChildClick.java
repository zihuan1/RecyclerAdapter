package com.zihuan.baseadapter.listener;

import android.view.View;

/**
 * Item子点击事件接口
 *
 * @author zihuan
 */
public interface ViewOnItemChildClick {
    /**
     * 子点击事件回调
     *
     * @param view 当前的view
     * @param position 当前点击的位置
     */
    void setOnChildClick(View view, int position);

    /**
     * 长按
     * @param view
     * @param position
     */
    void setOnChildLongClick(View view, int position);
}
