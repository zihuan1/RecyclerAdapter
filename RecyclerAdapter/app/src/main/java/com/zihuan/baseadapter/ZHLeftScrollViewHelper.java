package com.zihuan.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;


public class ZHLeftScrollViewHelper {
    private static ZHLeftScrollViewHelper mZhLeftScrollViewHelper;

    public static ZHLeftScrollViewHelper getInstance() {
        return mZhLeftScrollViewHelper != null ? mZhLeftScrollViewHelper : (mZhLeftScrollViewHelper = new ZHLeftScrollViewHelper());
    }

    private ZHLeftScrollView mZhLeftScrollView;

    /***
     * 设置滑动的view
     * @param zhLeftScrollView
     */
    public void setLastView(ZHLeftScrollView zhLeftScrollView) {
        if (isHasLastView() && mZhLeftScrollView != zhLeftScrollView) {
            mZhLeftScrollView.reset();
        }
        mZhLeftScrollView = zhLeftScrollView;
    }

    public ZHLeftScrollView getLastView() {
        return mZhLeftScrollView;
    }

    //是否有最后一个view
    private boolean isHasLastView() {
        return getLastView() != null;
    }

    public void bindRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("状态", "newState " + newState);
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING && isHasLastView()) {
                    mZhLeftScrollView.reset();
                }

            }
        });

    }

}
