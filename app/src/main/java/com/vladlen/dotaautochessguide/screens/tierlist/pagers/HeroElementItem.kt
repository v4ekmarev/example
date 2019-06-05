package com.vladlen.dotaautochessguide.screens.tierlist.pagers

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.model.vo.tier.TierHeroVo


class HeroElementItem : ConstraintLayout {

    private lateinit var img_tier_hero : ImageView
    private lateinit var txt_tier_position : TextView
    private lateinit var txt_name_tier_hero : TextView

    constructor(context: Context, tierHeroVo: TierHeroVo) : super(context){
        init(tierHeroVo)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs)

    private fun init(tierHeroVo: TierHeroVo) {
        View.inflate(context, R.layout.element_hero_pager_item, this)
        img_tier_hero = findViewById(R.id.img_tier_hero)
        txt_tier_position = findViewById(R.id.txt_tier_position)
        txt_name_tier_hero = findViewById(R.id.txt_name_tier_hero)

        txt_tier_position.text = tierHeroVo.index
        txt_name_tier_hero.text = tierHeroVo.heroName
        Glide.with(context).load(tierHeroVo.heroName).into(img_tier_hero)
    }
}