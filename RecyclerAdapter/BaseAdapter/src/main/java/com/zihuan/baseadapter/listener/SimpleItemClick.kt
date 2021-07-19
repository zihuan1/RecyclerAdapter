package com.zihuan.baseadapter.listener

import android.view.View

class SimpleItemClick : ViewOnItemClick, ViewOnItemLongClick {

    override fun setOnItemClickListener(view: View?, position: Int) {
        onItemClick(position)
    }

    override fun setOnItemLongClickListener(view: View?, position: Int) {
        onItemLongClick(position)
    }

    fun onItemClick(position: Int) {
    }

    fun onItemLongClick(position: Int) {
    }

}