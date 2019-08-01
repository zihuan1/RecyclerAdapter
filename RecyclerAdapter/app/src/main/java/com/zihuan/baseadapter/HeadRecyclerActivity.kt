package com.zihuan.baseadapter

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
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
        rav_layout.buildGridLayout(adapter, 2)
                .setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                    override fun onRefresh(refreshLayout: RefreshLayout) {
//                        刷新回调
                    }

                    override fun onLoadMore(refreshLayout: RefreshLayout) {
//                        加载更多回调

                    }

                })

        adapter.upDate(mDemoData, Item)
        bt_button.setOnClickListener {
            startActivity(Intent(this, HeadRecyclerActivity2::class.java))
        }
    }

    companion object {
        //static 代码段可以防止内存泄露
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ -> MaterialHeader(context) }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context).setDrawableSize(20f) }
        }
    }
}
