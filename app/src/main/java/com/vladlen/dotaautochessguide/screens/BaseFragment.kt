package com.vladlen.dotaautochessguide.screens

import android.support.v4.app.Fragment


open class BaseFragment : Fragment() {

    companion object {
        const val COST = "COST"
        const val CLASS = "CLASS"
        const val SPEC = "SPEC"
        const val TIER = "TIER"
    }
}
