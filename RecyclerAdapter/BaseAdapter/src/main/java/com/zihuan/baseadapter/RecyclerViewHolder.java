package com.zihuan.baseadapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author Created by zihuan on 2016/9/1.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private View view;
    private ViewOnItemClick onItemClick;
    private ViewOnItemLongClick longClick;
    public ViewDataBinding dataBinding;
    private ViewOnItemChildClick itemChildClick;

    public RecyclerViewHolder(View view, Object object) {
        super(view);
        instanceofObj(view, object);
    }

    public RecyclerViewHolder(View view, ViewDataBinding binding, Object object) {
        super(view);
        dataBinding = binding;
        instanceofObj(view, object);
    }

    public RecyclerViewHolder(View view, int type, Object object) {
        super(view);
        holderType = type;
        instanceofObj(view, object);
    }

    private void instanceofObj(View view, Object object) {
        this.view = view;
        if (object instanceof ViewOnItemClick) {
            this.onItemClick = (ViewOnItemClick) object;
            view.setOnClickListener(this);
        }
        if (object instanceof ViewOnItemLongClick) {
            this.longClick = (ViewOnItemLongClick) object;
            view.setOnLongClickListener(this);
        }
    }

    /***
     *
     * @param viewid 需要绑定单独点击事件的viewid
     */
    public void setOnChildClick(ViewOnItemChildClick childClick, int... viewid) {
        itemChildClick = childClick;
        for (int i = 0, size = viewid.length; i < size; i++) {
            final View view = getView(viewid[i]);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemChildClick.setOnChildClick(view.getId(), getAdapterPosition());
                }
            });
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
//        这里要返回true，否则在某些情况下会直接走点击事件
        return true;
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
