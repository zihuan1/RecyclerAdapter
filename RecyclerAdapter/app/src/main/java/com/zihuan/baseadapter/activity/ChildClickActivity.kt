package com.zihuan.baseadapter.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zihuan.baseadapter.*
import com.zihuan.baseadapter.listener.ViewOnItemChildClick
import com.zihuan.baseadapter.listener.ViewOnItemClick
import kotlinx.android.synthetic.main.activity_main.*


class ChildClickActivity : AppCompatActivity(), ViewOnItemClick, ViewOnItemChildClick {
    override fun setOnChildClick(view: View, position: Int) {
        Toast.makeText(this, "点击了 子 事件 ${view.id} $position", Toast.LENGTH_SHORT).show()
    }

    override fun setOnChildLongClick(view: View, position: Int) {
        Toast.makeText(this, "长按了 子 事件 ${view.id} $position", Toast.LENGTH_SHORT).show()
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
        demoAdapter.setOnChildClick(R.id.tv_add, R.id.tv_del)
        rav_layout.buildVerticalLayout(demoAdapter)
                .setData(mDemoData)
    }

}
