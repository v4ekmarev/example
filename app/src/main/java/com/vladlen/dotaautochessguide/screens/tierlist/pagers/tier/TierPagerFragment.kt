package com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.model.vo.tier.TierVo
import com.vladlen.dotaautochessguide.screens.guides.ItemOffsetDecoration
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.BasePagerFragment
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.HeroesPagerAdapter
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di.DaggerTierPagerComponent
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di.TierPagerComponentHolder
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.tier.di.TierPagerFactory
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import kotlinx.android.synthetic.main.fragment_hero_pager.*
import javax.inject.Inject

class TierPagerFragment : BasePagerFragment() {
    companion object {
        fun newInstance(): TierPagerFragment {
            val args = Bundle()
            val fragment = TierPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var adapter = HeroesPagerAdapter(object : OnCellDelegateClickListener<TierVo> {
        override fun onCellDelegateClick(item: TierVo) {

        }
    })

    @Inject
    lateinit var tierPagerFactory: TierPagerFactory
    lateinit var tierPagerViewModel: TierPagerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hero_pager, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TierPagerComponentHolder.instance.bindComponent(
                DaggerTierPagerComponent.builder()
                        .appComponent(AppComponentHolder.instance.component)
                        .build())
        TierPagerComponentHolder.instance.component?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tierPagerViewModel = ViewModelProviders.of(this, tierPagerFactory).get(TierPagerViewModel::class.java)
        rcl_pager_tier.layoutManager = GridLayoutManager(context, 2)
        context?.let { rcl_pager_tier.addItemDecoration(ItemOffsetDecoration(it, R.dimen.item_offset)) }
        rcl_pager_tier.adapter = adapter

        tierPagerViewModel.getTierList.observe(this, Observer { t ->
            when (t?.status) {
                ViewObject.Status.LOADING -> {
                    pb_load_tier.visibility = View.VISIBLE
                }
                ViewObject.Status.ERROR -> {
                    pb_load_tier.visibility = View.INVISIBLE
                }
                ViewObject.Status.SUCCESS -> {
                    pb_load_tier.visibility = View.INVISIBLE
                    adapter.items = t.data!!
                    adapter.notifyDataSetChanged()
                }
            }
        })

        tierPagerViewModel.runGetTier("tier")
    }

    override fun onDestroy() {
        super.onDestroy()
        TierPagerComponentHolder.instance.unbindComponent()
    }
}