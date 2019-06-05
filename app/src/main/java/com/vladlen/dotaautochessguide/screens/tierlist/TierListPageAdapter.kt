package com.vladlen.dotaautochessguide.screens.tierlist

import android.content.Context
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.clas.ClassPagerFragment
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.cost.CostPagerFragment
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.SpecPagerFragment
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.TierPagerFragment

class TierListPageAdapter(fm: FragmentManager?, var context: Context?) : FragmentStatePagerAdapter(fm) {
    private val NUM_ITEMS = 4

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> {
                TierPagerFragment.newInstance()
            }

            1 -> {
                CostPagerFragment.newInstance()
            }

            2 -> {
                ClassPagerFragment.newInstance()
            }

            3 -> {
               SpecPagerFragment.newInstance()
            }
            else -> TierPagerFragment.newInstance()
        }
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return NUM_ITEMS
    }

    /**
    <item>TIER</item>
    <item>COST</item>
    <item>CLASS</item>
    <item>SPEC</item>
     */
    override fun getPageTitle(position: Int): CharSequence? {
        return context!!.resources.getStringArray(R.array.heroes_page)[position]
    }
}
