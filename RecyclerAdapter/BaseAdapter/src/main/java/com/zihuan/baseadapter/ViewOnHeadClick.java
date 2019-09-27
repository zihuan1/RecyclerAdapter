package com.zihuan.baseadapter;

import android.view.View;

/**
 * 二级列表点击事件接口
 *
 * @author zihuan
 */
public interface ViewOnHeadClick {
    /**
     * 头部点击事件
     *
     * @param view
     * @param position
     */
    void setOnHeadClick(View view, int position);

    /**
     * item点击事件
     *
     * @param view
     * @param section
     * @param position
     */
    void setOnItemClick(View view, int section, int position);
}
