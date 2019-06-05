package com.vladlen.dotaautochessguide.screens.guides.adapter.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener

abstract class GuidesBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun initView(guide: GuideItemVo, click: OnCellDelegateClickListener<GuideItemVo>) {
        initItem(guide, click)
        itemView.setOnClickListener { click.onCellDelegateClick(guide) }
    }

    abstract fun initItem(guidItem: GuideItemVo, click: OnCellDelegateClickListener<GuideItemVo>)
}