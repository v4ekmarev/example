package com.vladlen.dotaautochessguide.screens.tierlist

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.tierlist.di.DaggerTierListComponent
import com.vladlen.dotaautochessguide.screens.tierlist.di.TierListComponentHolder
import com.vladlen.dotaautochessguide.screens.tierlist.di.TierListFactory
import kotlinx.android.synthetic.main.fragment_tier_list.*
import javax.inject.Inject

class TierListFragment : BaseFragment() {

    companion object {
        fun newInstance(): TierListFragment {
            val args = Bundle()
            val fragment = TierListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var tierListFactory: TierListFactory
    lateinit var tierListViewModel: TierListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tier_list, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TierListComponentHolder.instance.bindComponent(
            DaggerTierListComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        TierListComponentHolder.instance.component?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tierListViewModel = ViewModelProviders.of(this, tierListFactory).get(TierListViewModel::class.java)

        val tierListPageAdapter =
            TierListPageAdapter(fragmentManager, context)
        tier_list_pager.adapter = tierListPageAdapter
        sliding_tabs.setupWithViewPager(tier_list_pager)
    }

    override fun onDestroy() {
        super.onDestroy()
        TierListComponentHolder.instance.unbindComponent()
    }
}