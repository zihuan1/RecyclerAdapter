package com.zihuan.baseadapter.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.zihuan.baseadapter.DemoAdapter
import com.zihuan.baseadapter.R
import com.zihuan.baseadapter.listener.ViewOnItemClick
import kotlinx.android.synthetic.main.activity_main.*


class RefreshLoadMoreActivity : AppCompatActivity(), ViewOnItemClick {
    override fun setOnItemClickListener(view: View?, postion: Int) {
        startActivity(Intent(this, HeadRecyclerActivity::class.java))
    }

    var mDemoData = ArrayList<String>()
    lateinit var demoAdapter: DemoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        demoAdapter = DemoAdapter(this)
        for (i in 0..30) {
            mDemoData.add("昵称$i")
        }
        sr_layout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                sr_layout.finishLoadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                sr_layout.finishRefresh()
            }

        })
        rav_layout.buildVerticalLayout(demoAdapter)
//                .setData(mDemoData)
        demoAdapter.update = mDemoData
    }

}
