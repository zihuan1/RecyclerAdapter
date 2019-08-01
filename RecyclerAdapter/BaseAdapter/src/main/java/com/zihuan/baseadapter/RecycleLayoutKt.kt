package com.zihuan.app.base.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zihuan.baseadapter.*

@SuppressLint("StaticFieldLeak")
object RecycleLayoutKt {
   private var mContext: Context? = null

    //横向布局
    fun RecyclerView.initHorizontal() {
        mContext = this.context
        this.layoutManager = RecycleLayoutKt.RecycleViewBuilder.getLinLayoutManager(LinearLayoutManager.HORIZONTAL, this)
    }

    fun RecyclerView.initHorizontal(adapter: RecyclerView.Adapter<*>) {
        initHorizontal()
        this.adapter = adapter
    }


    //竖向布局
    fun RecyclerView.initVertical(): LinearLayoutManager {
        mContext = this.context
        this.layoutManager = RecycleLayoutKt.RecycleViewBuilder.getLinLayoutManager(LinearLayoutManager.VERTICAL, this)
        return this.layoutManager as LinearLayoutManager
    }

    fun RecyclerView.initVertical(adapter: RecyclerView.Adapter<*>) {
        initVertical()
        this.adapter = adapter
    }


    fun RecyclerView.initGrid(count: Int): GridLayoutManager {
        mContext = this.context
        this.layoutManager = RecycleLayoutKt.RecycleViewBuilder.getGridLayoutManager(count, this)
        return this.layoutManager as GridLayoutManager
    }

    fun RecyclerView.initGrid(count: Int, adapter: RecyclerView.Adapter<*>) {
        initGrid(count)
        this.adapter = adapter
    }


    private object RecycleViewBuilder {
        /***
         * orientation 0横向 1竖向
         */
        fun getLinLayoutManager(orientation: Int, view: RecyclerView): LinearLayoutManager {
            val layoutManager = object : LinearLayoutManager(mContext) {
                override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
                    super.onLayoutChildren(recycler, state)
                }
            }
            view.isNestedScrollingEnabled = false
            layoutManager.orientation = orientation
            return layoutManager
        }

        fun getGridLayoutManager(count: Int, view: RecyclerView): GridLayoutManager {
            view.isNestedScrollingEnabled = false
            return GridLayoutManager(mContext, count)
        }
    }


}