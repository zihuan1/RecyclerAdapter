package com.zihuan.baseadapter

import android.content.Context
import com.zihuan.baseadapter.activity.HeadRecyclerActivity
import java.util.*

class RecycleMultipleAdapter(context: Context) : StickyHeaderGridAdapter(context) {
    override fun getHeadLayoutResId(): Int {
        return R.layout.head_layout
    }

    override fun getItemLayoutResId(): Int {
        return R.layout.item_layout
    }


    private val mItemList = ArrayList<List<Entity>>()
    private val mHeadList = ArrayList<String>()


    fun upDate(head: List<String>, list: List<List<Entity>>) {
        mHeadList.clear()
        mItemList.clear()
        mItemList.addAll(list)
        mHeadList.addAll(head)
        notifyAllSectionsDataSetChanged()
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
        viewHolder.getTextView(R.id.tv_name).text = "你好"
        viewHolder.getView(R.id.rl_main).setOnLongClickListener {
            (mContext as HeadRecyclerActivity).itemTouchHelper.startDrag(viewHolder)
            return@setOnLongClickListener false
        }
    }

}