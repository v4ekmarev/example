package com.vladlen.dotaautochessguide.screens.tierlist.pagers

import android.support.v4.app.Fragment

open class BasePagerFragment : Fragment() {
    companion object {
        const val TIER = "TIER"
        const val CLASS = "CLASS"
        const val COST = "COST"
        const val SPEC = "SPEC"
    }
}