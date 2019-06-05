package com.vladlen.dotaautochessguide.screens.tierlist.pagers

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.holders.BasePagerViewHolder
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.holders.HeroPagerHolderPager

class HeroesPagerAdapter(private val callback: OnCellDelegateClickListener<TierVo>) : RecyclerView.Adapter<BasePagerViewHolder>() {
    var items = mutableListOf<TierVo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tier, parent, false)
        return HeroPagerHolderPager(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holderPager: BasePagerViewHolder, position: Int) {
        holderPager.initView(items[position], object : OnCellDelegateClickListener<TierVo> {
            override fun onCellDelegateClick(item: TierVo) {
                callback.onCellDelegateClick(item)
            }
        })
    }
}