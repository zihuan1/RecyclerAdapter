package com.zihuan.baseadapter

import android.content.Context

class DemoAdapter(`object`: Any?) : RecyclerAdapter(`object`) {
    override fun convert(holder: RecyclerViewHolder, position: Int, context: Context) {
        var entity = getEntity<String>(position)
        var item_text = holder.getTextView(R.id.item_text)
        item_text.text=entity
    }

    override fun getLayoutResId(): Int {
        return R.layout.rv_layout
    }
}