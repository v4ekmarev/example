package com.vladlen.dotaautochessguide.screens.guides.adapter.holders

import android.view.View
import com.bumptech.glide.Glide
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import kotlinx.android.synthetic.main.item_guid.view.*

class GuidItemHolder(itemView: View) : GuidesBaseViewHolder(itemView) {

    override fun initItem(guidItem: GuideItemVo, click: OnCellDelegateClickListener<GuideItemVo>) {
        itemView.txt_small_description_guid.text = guidItem.desc
        itemView.txt_title_guid.text = guidItem.title

        Glide.with(itemView.context)
            .load(guidItem.image)
            .centerCrop()
            .into(itemView.img_guide)
    }
}