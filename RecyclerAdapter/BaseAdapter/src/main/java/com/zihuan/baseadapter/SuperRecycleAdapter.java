package com.zihuan.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zihuan
 */
public class SuperRecycleAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    public List baseDatas = new ArrayList();

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {

    }

    /**
     * 获取数据的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return baseDatas == null || baseDatas.size() == 0 ? 0 : baseDatas.size();
    }


    public <T> T getEntity(int index) {
        return (T) (baseDatas.get(index));
    }

    public <T> ArrayList<T> getBaseData() {
        return (ArrayList<T>) baseDatas;
    }

    public void update(List list) {
        baseDatas.clear();
        baseDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 避免刷新闪烁
     *
     * @param list
     */
    public void updateAvoidFlashing(List list) {
        baseDatas.clear();
        baseDatas.addAll(list);
        notifyItemRangeChanged(0, baseDatas.size());

    }

    public int getListSize() {
        return baseDatas.size();
    }
}
