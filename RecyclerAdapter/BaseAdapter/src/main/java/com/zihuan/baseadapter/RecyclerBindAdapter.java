package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * Created by zihuan
 */
public class RecyclerBindAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    public static Context mContext;
    public int mRes;
    private Object mListener;
    private static RecyclerBindImageLoading mBindImageLoading;

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
        mListener = object;
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
        mBindImageLoading = RecyclerAdapterConfig.getInstance().getBindImageLoading();
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mRes, viewGroup, false);
        ViewDataBinding binding = DataBindingUtil.bind(view);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, binding, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        viewHolder.dataBinding.setVariable(BR.item, baseDatas.get(position));
    }


    /***
     *替换默认配置的图片加载
     */
    public void setImageLoader(RecyclerBindImageLoading imageLoader) {
        mBindImageLoading = imageLoader;
    }

    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView, String resource) {
        if (TextUtils.isEmpty(resource)) {
            return;
        }
        if (RecyclerAdapterConfig.getInstance().isInt(resource)) {
            mBindImageLoading.displayImage(mContext, imageView, Integer.valueOf(resource));
        } else {
            mBindImageLoading.displayImage(mContext, imageView, resource);
        }
    }

}
