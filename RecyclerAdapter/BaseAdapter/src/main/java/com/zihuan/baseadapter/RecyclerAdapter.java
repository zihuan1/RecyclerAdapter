package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author Zihuan
 */
public abstract class RecyclerAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    public Context mContext;
    /**
     * 子事件回调
     */
    ViewOnItemChildClick itemChildClick;
    /***
     **子事件的viewid
     * */
    int[] mViewId;
    private Object mObject;
    private boolean mUnClick;
    private boolean mUnLongClick;
    private boolean mUnChildClick;

    // TODO: 改成配置模式 getInstance ... 再加几个构造方法用来设置是否需要添加点击事件 并且子点击事件需要把view传回
    // TODO: 整理一下点击事件

    public RecyclerAdapter(Object object) {
        instanceofObj(object);
    }

    public RecyclerAdapter(Object object, List list) {
        instanceofObj(object);
        baseDatas.clear();
        baseDatas.addAll(list);
    }


    private void instanceofObj(Object object) {
        mObject = object;
        if (object instanceof Fragment) {
            mContext = ((Fragment) object).getContext();
        } else if (object instanceof Activity) {
            mContext = (Context) object;
        } else if (object instanceof View) {
            mContext = ((View) object).getContext();
        }
    }

    /**
     * 解绑当前适配器的点击事件
     */
    public void unViewOnItemClick() {
        mUnClick = true;
    }

    /**
     * 解绑当前适配器的长按点击事件
     */
    public void unViewOnLongItemClick() {
        mUnLongClick = true;
    }

    /**
     * 解绑当前适配器的子点击事件
     */
    public void unOnChildClick() {
        mUnChildClick = true;
    }

    /***
     * 当一个页面中有多个RecyclerView的时候用这个方法设置点击事件
     * @param object
     */
    public void setOnItemClick(ViewOnItemClick object) {
        mObject = object;
    }


    /***
     * 当一个页面中的item中的某个view需要单独添加点击事件的时候用这个方法
     * @param viewid
     */
    @Deprecated
    public RecyclerAdapter setOnChildClick(int... viewid) {
        if (mObject instanceof ViewOnItemChildClick) {
            itemChildClick = (ViewOnItemChildClick) mObject;
            mViewId = viewid;
        }
        return this;
    }

    public void setOnChildClick(ViewOnItemChildClick childClick, int... viewid) {
        itemChildClick = childClick;
        mViewId = viewid;
    }

    public RecyclerAdapter setOnChildClickId(int... viewid) {
        mViewId = viewid;
        return this;
    }

    public void setOnChildClick(ViewOnItemChildClick childClick) {
        itemChildClick = childClick;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res = getLayoutResId();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, mObject);
        if (null != itemChildClick) {
//            holder.unViewOnItemClick(mUnClick);
//            holder.unViewOnLongItemClick(mUnLongClick);
            if (!mUnChildClick) {
                holder.setOnChildClick(itemChildClick, mViewId);
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        convert(viewHolder, position, mContext);
    }

    /**
     * 设置数据
     *
     * @param holder
     * @param position
     * @param context
     */
    public abstract void convert(RecyclerViewHolder holder, int position, Context context);

    /**
     * 返回一个布局id
     *
     * @return
     */
    public abstract int getLayoutResId();

}
