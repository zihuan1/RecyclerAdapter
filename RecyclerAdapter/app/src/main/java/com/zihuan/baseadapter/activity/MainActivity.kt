package com.zihuan.baseadapter.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.zihuan.baseadapter.RecyclerAdapterConfig
import com.zihuan.baseadapter.R
import com.zihuan.baseadapter.RecyclerImageImp
import kotlinx.android.synthetic.main.activity_home.*


class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bt_list.setOnClickListener {
            startActivity(Intent(this, RefreshLoadMoreActivity::class.java))
        }
        bt_access_list.setOnClickListener {
            startActivity(Intent(this, HeadRecyclerActivity::class.java))
        }
        bt_anim.setOnClickListener {
            startActivity(Intent(this, HeadRecyclerActivity2::class.java))
        }
        bt_left_del.setOnClickListener {
            startActivity(Intent(this, LeftSlidDeleteActivity::class.java))
        }
        bt_left_del_2.setOnClickListener {
            startActivity(Intent(this, LeftSlidDeleteActivity2::class.java))
        }
        bt_child_click.setOnClickListener {
            startActivity(Intent(this, ChildClickActivity::class.java))
        }
        bt_data_bind.setOnClickListener {
            startActivity(Intent(this, DataBindActivity::class.java))
        }
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("PRETTY_LOGGER")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
        RecyclerAdapterConfig.getInstance().bindImageLoading = RecyclerImageImp()
    }
}