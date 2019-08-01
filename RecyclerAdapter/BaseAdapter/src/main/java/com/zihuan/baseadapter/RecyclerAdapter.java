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
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    public Context mContext;
    int mRes;//资源文件

    public RecyclerAdapter(Object object) {
        instanceofObj(object);
    }

    public RecyclerAdapter(Object object, List list) {
        instanceofObj(object);
        baseDatas.clear();
        baseDatas.addAll(list);
    }


    private void instanceofObj(Object object) {
        if (object instanceof Fragment) {
            mContext = ((Fragment) object).getContext();
        } else if (object instanceof Activity) {
            mContext = (Context) object;
        } else if (object instanceof View) {
            mContext = ((View) object).getContext();
        }
        if (object instanceof ViewOnItemClick) {
            this.onItemClick = (ViewOnItemClick) object;
        }
        if (object instanceof ViewOnItemLongClick) {
            this.longClick = (ViewOnItemLongClick) object;
        }
    }

    public void setOnItemClick(ViewOnItemClick itemClick) {
        onItemClick = itemClick;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res;
        if (mRes != 0) {
            res = mRes;
        } else {
            res = getLayoutResId();
        }
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, onItemClick, longClick);
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
