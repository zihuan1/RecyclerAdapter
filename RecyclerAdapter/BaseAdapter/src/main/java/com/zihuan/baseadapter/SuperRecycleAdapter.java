package com.zihuan.baseadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

public class SuperRecycleAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    public List baseDatas = new ArrayList();

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return baseDatas == null || baseDatas.size() == 0 ? 0 : baseDatas.size();
    }


    public <T> T getEntity(int index) {
        return (T) (baseDatas.get(index));
    }

    public void update(List list) {
        baseDatas.clear();
        baseDatas.addAll(list);
        notifyDataSetChanged();
    }

    public int getListSize() {
        return baseDatas.size();
    }
}
