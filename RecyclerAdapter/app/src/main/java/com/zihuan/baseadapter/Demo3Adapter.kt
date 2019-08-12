package com.zihuan.baseadapter

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import com.zihuan.baseadapter.slideswaphelper.SlideSwapAction

class Demo3Adapter(`object`: Any?) : SpecialHolderAdapter<Demo3Adapter.LeftScrollHolder>(`object`) {
    var mListener = `object`

    override fun convert(holder: LeftScrollHolder, position: Int, context: Context) {
        var entity = getEntity<String>(position)
        var item_text = holder.getTView<TextView>(R.id.item_text)
        var tv_del = holder.getTView<TextView>(R.id.tv_del)
        item_text.text = entity
        tv_del.setOnClickListener {
            baseDatas.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.rv_layout
    }

    override fun createHolder(view: View): LeftScrollHolder {
        return LeftScrollHolder(view, mListener!!)
    }

    class LeftScrollHolder(view: View, obj: Any) : RecyclerViewHolder(view, obj), SlideSwapAction {
        override fun getActionWidth(): Float {
            return dp2px(70f)
        }

        override fun ItemView(): View {
            return view.findViewById(R.id.item_text)
        }

        private fun dp2px(dpValue: Float): Float {
            val scale = view.context.resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, scale)
        }
    }


}