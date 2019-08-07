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
public abstract class SpecialHolderAdapter<T extends RecyclerViewHolder> extends SuperRecycleAdapter<T> {
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    public Context mContext;

    public SpecialHolderAdapter(Object object) {
        instanceofObj(object);
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

    //    T mType;
    //创建新View，被LayoutManager所调用
    @Override
    public T onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res = getLayoutResId();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        return createHolder(view);
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(T viewHolder, int position) {
        convert(viewHolder, position, mContext);
    }

    public abstract void convert(T holder, int position, Context context);

    public abstract int getLayoutResId();

    public abstract T createHolder(View view);

}
