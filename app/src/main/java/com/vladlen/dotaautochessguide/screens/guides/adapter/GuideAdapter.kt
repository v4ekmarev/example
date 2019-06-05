package com.vladlen.dotaautochessguide.screens.guides.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.screens.guides.adapter.holders.GuidItemHolder
import com.vladlen.dotaautochessguide.screens.guides.adapter.holders.GuidesBaseViewHolder

class GuideAdapter(private val callback: OnCellDelegateClickListener<GuideItemVo>) : RecyclerView.Adapter<GuidesBaseViewHolder>() {
    var items = mutableListOf<GuideItemVo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuidesBaseViewHolder {
        val guideItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_guid, parent, false)
        return GuidItemHolder(guideItemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holderBase: GuidesBaseViewHolder, position: Int) {
        holderBase.initView(items[position], object : OnCellDelegateClickListener<GuideItemVo> {
            override fun onCellDelegateClick(item: GuideItemVo) {
                callback.onCellDelegateClick(item)
            }
        })
    }
}