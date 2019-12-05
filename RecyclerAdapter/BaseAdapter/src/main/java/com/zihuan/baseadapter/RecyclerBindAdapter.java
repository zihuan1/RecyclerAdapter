package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


/**
 * Created by zihuan
 */
public class RecyclerBindAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    public static Context mContext;
    public int mRes;
    private Object mObject;
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
        mObject = object;
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

    /***
     * 当一个页面中有多个RecyclerView的时候用这个方法设置点击事件
     * @param object
     */
    public void setOnItemClick(ViewOnItemClick object) {
        if (object != null) {
            mObject = object;
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mRes, viewGroup, false);
        ViewDataBinding binding = DataBindingUtil.bind(view);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, binding, mObject);
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
