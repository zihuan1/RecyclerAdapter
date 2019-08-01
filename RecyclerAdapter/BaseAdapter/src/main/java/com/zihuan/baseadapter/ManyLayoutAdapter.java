package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/***
 * 多布局适配器
 */
public abstract class ManyLayoutAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    public List baseDatas = new ArrayList();
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    Activity mActivity;

    public int mListSize;

    public ManyLayoutAdapter(Object object) {
        instanceofObj(object);
    }

    //为butter准备的构造
    public ManyLayoutAdapter(List baseDatas, Object object) {
        this.baseDatas.clear();
        this.baseDatas.addAll(baseDatas);
        instanceofObj(object);
    }


    public ManyLayoutAdapter(Object object, ViewOnItemClick viewOnItemClick) {
        this.baseDatas.clear();
        this.baseDatas.addAll(baseDatas);
        onItemClick = viewOnItemClick;
    }


    private void instanceofObj(Object object) {
        if (object instanceof Fragment) {
            mActivity = ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            mActivity = (Activity) object;
        } else if (object instanceof View) {
            mActivity = (Activity) ((View) object).getContext();
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
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutResId().get(viewType), viewGroup, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(view, onItemClick, longClick, viewType);
        return holder;
    }


    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerViewHolder viewHolder, int position) {
        convert(viewHolder, position, mActivity, viewHolder.getHolderType());
    }


    public abstract void convert(RecyclerViewHolder holder, int position, Context context, int viewType);

    // 布局资源Id
    public abstract List<Integer> getLayoutResId();

    //获取数据的数量
    @Override
    public int getItemCount() {
        return baseDatas == null || baseDatas.size() == 0 ? 0 : baseDatas.size();
    }


//    @Override
//    public int getItemViewType(int position) {
//        return getLayoutResId().get(position);
//    }


    public boolean isNoNull(String str) {
        return str != null && str.length() != 0 && !"0".equals(str) && !TextUtils.isEmpty(str);
    }

    public void update(List list) {
        mListSize = list.size();
        this.baseDatas.clear();
        this.baseDatas.addAll(list);
        notifyDataSetChanged();
    }

}
