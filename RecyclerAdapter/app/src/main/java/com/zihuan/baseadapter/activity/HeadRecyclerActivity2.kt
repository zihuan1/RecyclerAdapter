package com.zihuan.baseadapter.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.baseadapter.Entity
import com.zihuan.baseadapter.R
import com.zihuan.baseadapter.RecycleMultipleAdapter
import com.zihuan.baseadapter.StickyHeaderGridLayoutManager
import kotlinx.android.synthetic.main.activity_headrecycle2.*


class HeadRecyclerActivity2 : AppCompatActivity() {
    var mDemoData = ArrayList<String>()
    var Item = ArrayList<ArrayList<Entity>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headrecycle2)
        for (i in 0..10) {
            mDemoData.add("head$i")
            var list = ArrayList<Entity>()
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            list.add(Entity())
            Item.add(list)
        }
        var adapter = RecycleMultipleAdapter(this)
        var layoutManager = StickyHeaderGridLayoutManager(8)
        rav_layout.buildGridLayout(adapter, 8)
        rav_layout.getRecyclerView().layoutManager = layoutManager
        adapter.upDate(mDemoData, Item)
        rav_layout.getRecyclerView().isNestedScrollingEnabled = true
    }

}
