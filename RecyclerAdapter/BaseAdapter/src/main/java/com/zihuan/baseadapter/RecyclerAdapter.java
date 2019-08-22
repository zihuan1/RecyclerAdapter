package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by zihuan
 */
public abstract class RecyclerAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    public Context mContext;

    public RecyclerAdapter(Object object) {
        instanceofObj(object);
    }

    public RecyclerAdapter(Object object, List list) {
        instanceofObj(object);
        baseDatas.clear();
        baseDatas.addAll(list);
    }

    private Object mListener;

    private void instanceofObj(Object object) {
        mListener = object;
        if (object instanceof Fragment) {
            mContext = ((Fragment) object).getContext();
        } else if (object instanceof Activity) {
            mContext = (Context) object;
        } else if (object instanceof View) {
            mContext = ((View) object).getContext();
        }

    }

    public void setOnItemClick(Object object) {
        mListener = object;

    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res = getLayoutResId();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, mListener);
        return holder;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        convert(viewHolder, position, mContext);
    }

    public abstract void convert(RecyclerViewHolder holder, int position, Context context);

    public abstract int getLayoutResId();

}
