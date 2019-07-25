package com.zihuan.baseadapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.Recycler;
import android.support.v7.widget.RecyclerView.State;

public class RecycleLayout {
    private static Context mContext;

    public final void initHorizontal(RecyclerView $receiver) {
        mContext = $receiver.getContext();
        $receiver.setLayoutManager(new RecycleViewBuilder().getLinLayoutManager(0, $receiver));
    }

    public final void initHorizontal(RecyclerView $receiver, Adapter adapter) {
        this.initHorizontal($receiver);
        $receiver.setAdapter(adapter);
    }

    public final LinearLayoutManager initVertical(RecyclerView $receiver) {
        mContext = $receiver.getContext();
        $receiver.setLayoutManager(new RecycleViewBuilder().getLinLayoutManager(1, $receiver));
        LayoutManager var10000 = $receiver.getLayoutManager();
        return (LinearLayoutManager) var10000;

    }

    public final void initVertical(RecyclerView $receiver, Adapter adapter) {
        this.initVertical($receiver);
        $receiver.setAdapter(adapter);
    }

    public final GridLayoutManager initGrid(RecyclerView $receiver, int count) {
        mContext = $receiver.getContext();
        $receiver.setLayoutManager(new RecycleViewBuilder().getGridLayoutManager(count, $receiver));
        LayoutManager var10000 = $receiver.getLayoutManager();
        return (GridLayoutManager) var10000;
    }

    public final void initGrid(RecyclerView $receiver, int count, Adapter adapter) {
        this.initGrid($receiver, count);
        $receiver.setAdapter(adapter);
    }


    private static class RecycleViewBuilder {

        public final LinearLayoutManager getLinLayoutManager(int orientation, RecyclerView view) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext) {
                public void onLayoutChildren(@Nullable Recycler recycler, @Nullable State state) {
                    super.onLayoutChildren(recycler, state);
                }
            };
            view.setNestedScrollingEnabled(false);
            layoutManager.setOrientation(orientation);
            return layoutManager;
        }

        public final GridLayoutManager getGridLayoutManager(int count, RecyclerView view) {
            view.setNestedScrollingEnabled(false);
            return new GridLayoutManager(mContext, count);
        }

    }
}
