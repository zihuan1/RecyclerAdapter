package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Created by zihuan
 * 建议纯文字使用
 */
public class RecyclerBindAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    public Context mContext;
    public int mRes;

    public RecyclerBindAdapter(Object object, int layoutRes) {
        instanceofObj(object, layoutRes);
    }


    public RecyclerBindAdapter(Object object, int layoutRes, boolean disableClick) {
        instanceofObj(object, layoutRes);
        if (disableClick) {
            this.onItemClick = null;
            this.longClick = null;
        }
    }

    private void instanceofObj(Object object, int res) {
        mRes = res;
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


    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mRes, viewGroup, false);
        ViewDataBinding binding = DataBindingUtil.bind(view);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, onItemClick, longClick, binding);
        return holder;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.dataBinding.setVariable(BR.item, baseDatas.get(position));
    }


}
