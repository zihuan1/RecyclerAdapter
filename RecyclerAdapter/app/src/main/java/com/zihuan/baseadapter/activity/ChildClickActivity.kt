package com.zihuan.baseadapter.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.zihuan.baseadapter.*
import kotlinx.android.synthetic.main.activity_main.*


class ChildClickActivity : AppCompatActivity(), ViewOnItemClick, ViewOnItemChildClick {
    override fun setOnChildClick(view_id: Int, position: Int) {
        Toast.makeText(this, "点击了 子 事件 $view_id $position", Toast.LENGTH_SHORT).show()
    }

    override fun setOnItemClickListener(view: View?, postion: Int) {
        Toast.makeText(this, "点击了Item上的事件 $postion", Toast.LENGTH_SHORT).show()
    }

    var mDemoData = ArrayList<String>()
    lateinit var demoAdapter: Demo1Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        demoAdapter = Demo1Adapter(this)
        for (i in 0..30) {
            mDemoData.add("昵称$i")
        }
        demoAdapter.setOnChildCilck(R.id.tv_add, R.id.tv_del)
        rav_layout.buildVerticalLayout(demoAdapter)
                .setData(mDemoData)
    }

}
