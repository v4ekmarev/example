package com.vladlen.dotaautochessguide.screens.items.adapter.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.vladlen.dotaautochessguide.model.vo.item.IItem
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener

abstract class ItemBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun initView(item: IItem, click: OnCellDelegateClickListener<IItem>) {
        initItem(item, click)
    }

    abstract fun initItem(item: IItem, click: OnCellDelegateClickListener<IItem>)
}