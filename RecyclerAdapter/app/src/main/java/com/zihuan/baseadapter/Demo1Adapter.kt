package com.zihuan.baseadapter

import android.content.Context
import com.zihuan.view.crvlibrary.ZRecyclerData

class Demo1Adapter(`object`: Any?) : RecyclerAdapter(`object`), ZRecyclerData {
    override fun convert(holder: RecyclerViewHolder, position: Int, context: Context) {
        var entity = getEntity<String>(position)
        var item_text = holder.getTextView(R.id.item_text)
        item_text.text=entity
    }

    override fun getLayoutResId(): Int {
        return R.layout.rv_click_layout
    }
}