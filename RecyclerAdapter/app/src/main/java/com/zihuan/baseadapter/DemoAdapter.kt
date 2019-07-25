package com.zihuan.baseadapter

import android.content.Context

class DemoAdapter(`object`: Any?) : RecyclerAdapter(`object`) {
    override fun convert(holder: RecyclerViewHolder, position: Int, context: Context) {
        var entity = getEntity<String>(position)
        var tv_name = holder.getTextView(R.id.tv_name)
        tv_name.text=entity
    }

    override fun getLayoutResId(): Int {
        return R.layout.rv_layout
    }
}