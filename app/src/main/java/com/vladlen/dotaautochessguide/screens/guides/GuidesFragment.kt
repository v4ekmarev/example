package com.vladlen.dotaautochessguide.screens.guides

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.model.vo.guide.GuideItemVo
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.guides.adapter.GuideAdapter
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.guides.di.DaggerGuidesComponent
import com.vladlen.dotaautochessguide.screens.guides.di.GuidesComponentHolder
import com.vladlen.dotaautochessguide.screens.guides.di.GuidesFactory
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.GuideContentFragment
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import kotlinx.android.synthetic.main.fragment_guides.*
import javax.inject.Inject





class GuidesFragment : BaseFragment() {

    companion object {
        fun newInstance(): GuidesFragment {
            val args = Bundle()
            val fragment = GuidesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var guidesFactory: GuidesFactory
    lateinit var guidesViewModel: GuidesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GuidesComponentHolder.instance.bindComponent(
            DaggerGuidesComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        GuidesComponentHolder.instance.component?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_guides, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        guidesViewModel = ViewModelProviders.of(this, guidesFactory).get(GuidesViewModel::class.java)

        rcl_guides.layoutManager = GridLayoutManager(context, 2)
        context?.let{rcl_guides.addItemDecoration(ItemOffsetDecoration(it, R.dimen.item_offset))}

        val guideAdapter = GuideAdapter(object : OnCellDelegateClickListener<GuideItemVo> {
            override fun onCellDelegateClick(item: GuideItemVo) {
                fragmentManager!!.beginTransaction().replace(R.id.container, GuideContentFragment.newInstance(item.id)).addToBackStack(null).commit()
            }
        })
        rcl_guides.adapter = guideAdapter

        guidesViewModel.getGuideList.observe(this, Observer { t ->
            when (t?.status) {
                ViewObject.Status.LOADING -> {
                    pb_load_guides.visibility = View.VISIBLE
                }
                ViewObject.Status.ERROR -> {
                    pb_load_guides.visibility = View.INVISIBLE
                }
                ViewObject.Status.SUCCESS -> {
                    pb_load_guides.visibility = View.INVISIBLE
                    guideAdapter.items = t.data!!
                    guideAdapter.notifyDataSetChanged()
                }
            }
        })

        guidesViewModel.runGetGuideItemList()
    }

    override fun onDestroy() {
        super.onDestroy()
        GuidesComponentHolder.instance.unbindComponent()
    }
}