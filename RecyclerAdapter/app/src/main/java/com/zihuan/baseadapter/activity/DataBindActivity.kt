package com.zihuan.baseadapter.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.zihuan.baseadapter.*
import kotlinx.android.synthetic.main.activity_main.*


class DataBindActivity : AppCompatActivity() {


    var mDemoData = ArrayList<Entity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for (i in 0..30) {
            var entity = Entity()
            entity.title = "昵称$i"
            if (i % 2 == 0)
                entity.url = ""
//                entity.url = "https://pic4.zhimg.com/80/v2-a5ae7ffda2ff33d399ac1ac8a72582aa_hd.jpg"
            else entity.url = R.mipmap.ic_launcher.toString()
            mDemoData.add(entity)
        }
        rav_layout.buildVerticalLayout(RecyclerBindAdapter(this, R.layout.rv_bind_layout))
                .setData(mDemoData)
    }

}
