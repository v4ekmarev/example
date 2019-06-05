package com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec

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
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.guides.ItemOffsetDecoration
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.HeroesPagerAdapter
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di.DaggerSpecPagerComponent
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di.SpecPagerComponentHolder
import com.vladlen.dotaautochessguide.screens.tierlist.pagers.spec.di.SpecPagerFactory
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import kotlinx.android.synthetic.main.fragment_hero_pager.*
import javax.inject.Inject

class SpecPagerFragment : BaseFragment() {
    companion object {

        fun newInstance(): SpecPagerFragment {
            val args = Bundle()
            val fragment = SpecPagerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var adapter = HeroesPagerAdapter(object : OnCellDelegateClickListener<TierVo> {
        override fun onCellDelegateClick(item: TierVo) {

        }
    })

    @Inject
    lateinit var specPagerFactory: SpecPagerFactory
    lateinit var specPagerViewModel: SpecPagerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_hero_pager, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SpecPagerComponentHolder.instance.bindComponent(
            DaggerSpecPagerComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        SpecPagerComponentHolder.instance.component?.inject(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        specPagerViewModel = ViewModelProviders.of(this, specPagerFactory).get(SpecPagerViewModel::class.java)
        rcl_pager_tier.layoutManager = GridLayoutManager(context, 2)
        context?.let { rcl_pager_tier.addItemDecoration(ItemOffsetDecoration(it, R.dimen.item_offset)) }
        rcl_pager_tier.adapter = adapter

        specPagerViewModel.getTierList.observe(this, Observer { t ->
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

        specPagerViewModel.runGetTier("spec")
    }

    override fun onDestroy() {
        super.onDestroy()
        SpecPagerComponentHolder.instance.unbindComponent()
    }
}