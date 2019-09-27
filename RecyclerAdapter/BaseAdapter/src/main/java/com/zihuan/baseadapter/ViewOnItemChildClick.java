package com.zihuan.baseadapter;

/**
 * Item子点击事件接口
 *
 * @author zihuan
 */
public interface ViewOnItemChildClick {
    /**
     * 子点击事件回调
     *
     * @param view_id  当前的viewid
     * @param position 当前点击的位置
     */
    void setOnChildClick(int view_id, int position);
}
