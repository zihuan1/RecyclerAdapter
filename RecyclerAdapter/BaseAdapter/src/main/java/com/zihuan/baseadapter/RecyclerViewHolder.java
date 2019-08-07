package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by zihuan on 2016/9/1.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    View view;
    ViewOnItemClick onItemClick;
    ViewOnItemLongClick longClick;
    ViewDataBinding dataBinding;

    public RecyclerViewHolder(View view) {
        super(view);
        instanceofObj(view);
    }

    public RecyclerViewHolder(View view, ViewDataBinding binding) {
        super(view);
        dataBinding = binding;
        instanceofObj(view);
    }

    public RecyclerViewHolder(View view, int type) {
        super(view);
        holderType = type;
        instanceofObj(view);
    }

    private void instanceofObj(View view) {
        this.view = view;
        if (view.getContext() instanceof ViewOnItemClick) {
            this.onItemClick = (ViewOnItemClick) view.getContext();
            view.setOnClickListener(this);
        }
        if (view.getContext() instanceof ViewOnItemLongClick) {
            this.longClick = (ViewOnItemLongClick) view.getContext();
            view.setOnLongClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (onItemClick != null) {
            onItemClick.setOnItemClickListener(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClick != null) {
            longClick.setOnItemLongClickListener(v, getAdapterPosition());
        }
        return false;
    }

    public View getView(int id) {
        return view.findViewById(id);
    }

    public <T extends View> T getTView(int id) {
        return view.findViewById(id);
    }

    public TextView getTextView(int id) {
        return view.findViewById(id);
    }

    public ImageView getImageView(int id) {
        return view.findViewById(id);
    }

    int holderType;

    public View getView() {

        return view;
    }

    public int getHolderType() {
        return holderType;
    }

    public void setHolderType(int holderType) {
        this.holderType = holderType;
    }
}
