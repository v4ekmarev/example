package com.vladlen.dotaautochessguide.screens.items.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.model.vo.item.IItem
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.items.adapter.holders.ItemBaseViewHolder
import com.vladlen.dotaautochessguide.screens.items.adapter.holders.ItemHolder

class ItemsAdapter(private val callback: OnCellDelegateClickListener<IItem>) : RecyclerView.Adapter<ItemBaseViewHolder>() {
    var items = mutableListOf<IItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemBaseViewHolder {
        val itemItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guid, parent, false)

        return ItemHolder(itemItemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holderBase: ItemBaseViewHolder, position: Int) {
        holderBase.initView(items[position], object : OnCellDelegateClickListener<IItem> {
            override fun onCellDelegateClick(item: IItem) {
                callback.onCellDelegateClick(item)
            }
        })
    }
}