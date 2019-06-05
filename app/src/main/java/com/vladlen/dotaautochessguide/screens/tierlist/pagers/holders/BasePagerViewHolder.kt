package com.vladlen.dotaautochessguide.screens.tierlist.pagers.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.HeroElementItem
import kotlinx.android.synthetic.main.item_tier.view.*

abstract class BasePagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun initView(tierVo: TierVo, orderClick: OnCellDelegateClickListener<TierVo>) {
        initItem(tierVo)
        for (tierHeroVo in tierVo.tierHeroList) {
            itemView.ll_container_tier_hero.addView(HeroElementItem(itemView.context,tierHeroVo))
        }
    }

    abstract fun initItem(tierVo: TierVo)
}