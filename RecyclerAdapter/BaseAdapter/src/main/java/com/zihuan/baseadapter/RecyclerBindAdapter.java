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

import com.zihuan.baseadapter.listener.RecyclerBindImageLoading;
import com.zihuan.baseadapter.listener.ViewOnItemChildClick;
import com.zihuan.baseadapter.listener.ViewOnItemClick;
import com.zihuan.baseadapter.listener.ViewOnItemLongClick;


/**
 * Created by Zihuan
 */
public class RecyclerBindAdapter extends SuperRecycleAdapter<RecyclerViewHolder> {
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    /**
     * 子事件回调
     */
    ViewOnItemChildClick itemChildClick;
    public static Context mContext;
    public int mRes;
    private Object mObject;
    private static RecyclerBindImageLoading mBindImageLoading;
    private boolean mUnChildClick;

    /***
     **子事件的viewid
     * */
    int[] mViewId;
    public RecyclerBindAdapter(Object obj, int layoutRes) {
        instanceofObj(obj, layoutRes);
    }

    public RecyclerBindAdapter(Object obj, int layoutRes, boolean disableClick) {
        instanceofObj(obj, layoutRes);
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
     * @param obj
     */
    public void setOnItemClick(ViewOnItemClick obj) {
        if (obj != null) {
            mObject = obj;
        }
    }
    /***
     * 当一个页面中的item中的某个view需要单独添加点击事件的时候用这个方法
     * @param viewid
     */
    public RecyclerBindAdapter setOnChildClick(int... viewid) {
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

    public RecyclerBindAdapter setOnChildClickId(int... viewid) {
        mViewId = viewid;
        return this;
    }

    public void setOnChildClick(ViewOnItemChildClick childClick) {
        itemChildClick = childClick;
    }

    /**
     * 解绑当前适配器的子点击事件
     * 初始化的时候调用有效
     */
//    public void unOnChildClick() {
//        mUnChildClick = true;
//    }
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mRes, viewGroup, false);
        ViewDataBinding binding = DataBindingUtil.bind(view);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, binding, mObject);
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
        viewHolder.dataBinding.setVariable(BR.item, baseDatas.get(position));
    }


    /***
     *替换默认配置的图片加载
     */
    public void setImageLoader(RecyclerBindImageLoading imageLoader) {
        mBindImageLoading = imageLoader;
    }

    @BindingAdapter({"imageUrl"})
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
