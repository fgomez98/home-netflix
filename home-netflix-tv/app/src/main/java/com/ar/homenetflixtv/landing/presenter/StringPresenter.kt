package com.ar.homenetflixtv.landing.presenter

import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.Presenter

class StringPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val textView = TextView(parent?.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
        }
        return ViewHolder(textView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        (viewHolder?.view as TextView).text = item.toString()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }
}