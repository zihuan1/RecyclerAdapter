package com.zihuan.baseadapter.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        var adapter = CompatibleDatabindAdapter(this, R.layout.rv_bind_layout)
        adapter.setOnItemClick { _, _ ->
            Log.e("点击", "点击")
        }
        rav_layout.buildVerticalLayout(adapter)
                .setData(mDemoData)
    }

}
