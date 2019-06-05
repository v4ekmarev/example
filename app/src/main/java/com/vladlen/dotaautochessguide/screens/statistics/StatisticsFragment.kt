package com.vladlen.dotaautochessguide.screens.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.items.di.ItemsComponentHolder
import com.vladlen.dotaautochessguide.screens.statistics.di.DaggerStatisticsComponent
import com.vladlen.dotaautochessguide.screens.statistics.di.StatisticsComponentHolder

class StatisticsFragment : BaseFragment() {

    companion object {
        fun newInstance(): StatisticsFragment {
            val args = Bundle()
            val fragment = StatisticsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        StatisticsComponentHolder.instance.bindComponent(
            DaggerStatisticsComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        StatisticsComponentHolder.instance.component?.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ItemsComponentHolder.instance.unbindComponent()
    }
}