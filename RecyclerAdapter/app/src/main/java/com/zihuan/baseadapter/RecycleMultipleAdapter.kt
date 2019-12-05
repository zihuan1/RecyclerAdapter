package com.zihuan.baseadapter

import android.content.Context
import com.zihuan.view.crvlibrary.ZRecyclerData
import java.util.*
import kotlin.collections.ArrayList

class RecycleMultipleAdapter(context: Context) : StickyHeaderGridAdapter(context), ZRecyclerData {
    override fun getHeadLayoutResId(): Int {
        return R.layout.head_layout
    }

    override fun getItemLayoutResId(): Int {
        return R.layout.item_layout
    }


    private val mHeadList = ArrayList<String>()
    private val mItemList = ArrayList<List<Entity>>()


    fun upDate(head: ArrayList<String>, list: ArrayList<ArrayList<Entity>>) {
        mHeadList.clear()
        mItemList.clear()
        mItemList.addAll(list)
        mHeadList.addAll(head)
        notifyAllSectionsDataSetChanged()
    }

    fun upDateMove(list: List<List<Entity>>, fromPosition: Int, toPosition: Int) {
        mItemList.clear()
        mItemList.addAll(list)
        calculateSections()
        notifyItemMoved(fromPosition, toPosition)
    }


    override fun getSectionCount(): Int {
        return mItemList.size
    }

    override fun getSectionItemCount(section: Int): Int {
        return mItemList[section].size
    }


    override fun onBindHeaderViewHolder(viewHolder: RecyclerViewHolder, section: Int) {
        viewHolder.getTextView(R.id.tv_name).text = mHeadList[section]
    }


    override fun onBindItemViewHolder(viewHolder: RecyclerViewHolder, section: Int, position: Int) {
        val entity = mItemList[section][position]
        viewHolder.getImageView(R.id.iv_head).setImageResource(R.mipmap.ic_launcher)
        viewHolder.getTextView(R.id.tv_name).text = entity.title
//        viewHolder.getView(R.id.rl_main).setOnLongClickListener {
//            if (mContext is HeadRecyclerActivity)
//                (mContext as HeadRecyclerActivity).itemTouchHelper.startDrag(viewHolder)
//            return@setOnLongClickListener false
//        }
    }

}