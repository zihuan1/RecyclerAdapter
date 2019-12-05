package com.zihuan.baseadapter;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragItemTouchHelper extends ItemTouchHelper.Callback {

    RecycleMultipleAdapter mAdapter;
    List<ArrayList<Entity>> mList;

    public DragItemTouchHelper(RecyclerView.Adapter adapter, List<ArrayList<Entity>> list) {
        mAdapter = (RecycleMultipleAdapter) adapter;
        mList = list;
    }


    //  设置是否滑动时间，以及拖拽的方向
    //  如果是列表布局的话则拖拽方向有DOWN和UP，
    //  如果是网格布局的话有DOWN和UP和LEFT和RIGHT4个方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    //    正在拖动中
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //得到当拖拽的viewHolder的Position
        int fromPosition = viewHolder.getAdapterPosition();
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        int fromIndices1 = mAdapter.getSectionIndices()[fromPosition];//开始级别位置
        int fromOffset1 = mAdapter.getItemSectionOffset(fromIndices1, fromPosition);//开始的位置
        int toOffset1 = 0;//目标位置

        int fromIndices2 = mAdapter.getSectionIndices()[toPosition];//目标级别位置
        int toOffset2 = 0;//越级的目标位置

        if (fromIndices1 == fromIndices2) {
            toOffset1 = mAdapter.getItemSectionOffset(fromIndices1, toPosition);
            Log.e("\n onMove ", " fromPosition " + fromPosition + " toPosition " + toPosition
                    + "\n fromPositionSections" + fromIndices1
                    + "\n fromPositionOffset" + fromOffset1
                    + "\n toPositionOffset" + toOffset1
            );
        } else {
            toOffset2 = mAdapter.getItemSectionOffset(fromIndices2, toPosition);
            Log.e("\n跨集合", "跨集合" + fromIndices1 + " → " + fromIndices2);
            Log.e("\n onMove ", " fromPosition " + fromPosition + " toPosition " + toPosition
                    + "\n fromPositionSections" + fromIndices1
                    + "\n fromPositionOffset" + fromOffset1
                    + "\n toPositionSections" + fromIndices2
                    + "\n toPositionOffset" + toOffset2
            );
        }

        if (toOffset1 >= 0 && toOffset2 >= 0) {
            if (fromIndices1 == fromIndices2) {//没有越级
                if (fromOffset1 < toOffset1) {
                    for (int i = fromOffset1; i < toOffset1; i++) {
                        Collections.swap(mList.get(fromIndices1), i, i + 1);
                    }
                } else {
                    for (int i = fromOffset1; i > toOffset1; i--) {
                        Collections.swap(mList.get(fromIndices1), i, i - 1);
                    }
                }
            } else {//越级了
                Entity entity = mList.get(fromIndices1).get(fromOffset1);
                mList.get(fromIndices1).remove(entity);
                mList.get(fromIndices2).add(toOffset2, entity);
            }
            mAdapter.upDateMove(mList,fromPosition,toPosition);
        }
        return true;
    }

    //拖拽完成
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
    }

    //    选中view
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    //    放开view
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    //重写拖拽不可用
    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }

}
