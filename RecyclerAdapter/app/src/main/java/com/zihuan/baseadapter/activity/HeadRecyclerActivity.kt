package com.zihuan.baseadapter.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.baseadapter.Entity
import com.zihuan.baseadapter.R
import com.zihuan.baseadapter.RecycleMultipleAdapter
import com.zihuan.baseadapter.listener.ViewOnHeadClick
import kotlinx.android.synthetic.main.activity_headrecycle.*


class HeadRecyclerActivity : AppCompatActivity(), ViewOnHeadClick {
    override fun setOnHeadClick(view: View?, position: Int) {
        Toast.makeText(this, mDemoData[position], Toast.LENGTH_SHORT).show()
    }

    override fun setOnItemClick(view: View?, section: Int, position: Int) {
        Toast.makeText(this, "section $section position $position", Toast.LENGTH_SHORT).show()
    }

    var mDemoData = ArrayList<String>()
    var Item = ArrayList<ArrayList<Entity>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headrecycle)
        for (i in 0..30) {
            mDemoData.add("head$i")
            var list = ArrayList<Entity>()
            for (j in 0..9) {
                list.add(Entity("item $j"))
            }
            Item.add(list)
        }
        var adapter = RecycleMultipleAdapter(this)
        rav_layout.buildGridLayout(adapter, 1)

        adapter.upDate(mDemoData, Item)

//        itemTouchHelper = ItemTouchHelper(DragItemTouchHelper(adapter, Item as List<ArrayList<Entity>>))
//        itemTouchHelper.attachToRecyclerView(rav_layout.getRecyclerView())

    }

//    lateinit var itemTouchHelper: ItemTouchHelper

}
