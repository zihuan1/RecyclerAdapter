package com.zihuan.baseadapter;

import android.databinding.ViewDataBinding;
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

    public RecyclerViewHolder(View view, ViewOnItemClick onItemClick, ViewOnItemLongClick longClick) {
        super(view);
        this.view = view;
        if (onItemClick != null) {
            this.onItemClick = onItemClick;
            view.setOnClickListener(this);
        }
        if (longClick != null) {
            this.longClick = longClick;
            view.setOnLongClickListener(this);
        }
    }

    public RecyclerViewHolder(View view, ViewOnItemClick onItemClick, ViewOnItemLongClick longClick, ViewDataBinding binding) {
        super(view);
        this.view = view;
        dataBinding = binding;
        if (onItemClick != null) {
            this.onItemClick = onItemClick;
            view.setOnClickListener(this);
        }
        if (longClick != null) {
            this.longClick = longClick;
            view.setOnLongClickListener(this);
        }
    }

    public RecyclerViewHolder(View view, ViewOnItemClick onItemClick, ViewOnItemLongClick longClick, int type) {
        super(view);
        this.view = view;
        holderType = type;
        if (onItemClick != null) {
            this.onItemClick = onItemClick;
            view.setOnClickListener(this);
        }
        if (longClick != null) {
            this.longClick = longClick;
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
