package com.zihuan.baseadapter.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.zihuan.baseadapter.DemoAdapter
import com.zihuan.baseadapter.R
import com.zihuan.baseadapter.ViewOnItemClick
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
        rav_layout.buildVerticalLayout(demoAdapter)
                .setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
                    override fun onRefresh(refreshLayout: RefreshLayout) {
//                        刷新回调
                    }

                    override fun onLoadMore(refreshLayout: RefreshLayout) {
//                        加载更多回调

                    }

                })
                .setData(mDemoData)
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
