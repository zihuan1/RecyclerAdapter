package com.zihuan.baseadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * @author Zihuan
 */
public abstract class SpecialHolderAdapter<T extends RecyclerViewHolder> extends SuperRecycleAdapter<T> {
    public Context mContext;

    public SpecialHolderAdapter(Object object) {
        instanceofObj(object);
    }

    private void instanceofObj(Object object) {
        if (object instanceof Fragment) {
            mContext = ((Fragment) object).getContext();
        } else if (object instanceof Activity) {
            mContext = (Context) object;
        } else if (object instanceof View) {
            mContext = ((View) object).getContext();
        }
    }

    @Override
    public T onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        int res = getLayoutResId();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(res, viewGroup, false);
        return createHolder(view);
    }


    @Override
    public void onBindViewHolder(T viewHolder, int position) {
        convert(viewHolder, position, mContext);
    }

    public abstract void convert(T holder, int position, Context context);

    public abstract int getLayoutResId();

    public abstract T createHolder(View view);

}
