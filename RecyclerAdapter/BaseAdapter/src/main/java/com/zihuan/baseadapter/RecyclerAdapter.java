package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * @author Created by zihuan
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

    /***
     * 当一个页面中有多个RecyclerView的时候用这个方法设置点击事件
     * @param object
     */
    public void setOnItemClick(Object object) {
        mObject = object;
    }


    /***
     * 当一个页面中的item中的某个view需要单独添加点击事件的时候用这个方法
     * @param viewid
     */
    public void setOnChildCilck(int... viewid) {
        if (mContext instanceof ViewOnItemChildClick) {
            itemChildClick = (ViewOnItemChildClick) mContext;
            mViewId = viewid;
        }
    }

    public void setOnChildCilck(ViewOnItemChildClick childClick, int... viewid) {
        itemChildClick = childClick;
        mViewId = viewid;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res = getLayoutResId();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, mObject);
        if (null != itemChildClick) {
            holder.setOnChildClick(itemChildClick, mViewId);
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
