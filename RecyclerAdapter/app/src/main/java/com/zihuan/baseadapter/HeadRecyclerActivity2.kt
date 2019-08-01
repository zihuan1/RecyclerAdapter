package com.zihuan.baseadapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_headrecycle.*
import kotlinx.android.synthetic.main.recycle_layout.view.*


class HeadRecyclerActivity2 : AppCompatActivity() {
    var mDemoData = ArrayList<String>()
    var Item = ArrayList<ArrayList<Entity>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_headrecycle2)
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
        var layoutManager = StickyHeaderGridLayoutManager(2)
        rav_layout.buildGridLayout(adapter, 2)
                .setLayoutManager(layoutManager)
        adapter.upDate(mDemoData, Item)
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
