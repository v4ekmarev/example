package com.vladlen.dotaautochessguide.screens.items

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.model.vo.item.IItem
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.guides.ItemOffsetDecoration
import com.vladlen.dotaautochessguide.screens.guides.adapter.OnCellDelegateClickListener
import com.vladlen.dotaautochessguide.screens.items.adapter.ItemsAdapter
import com.vladlen.dotaautochessguide.screens.items.di.DaggerItemsComponent
import com.vladlen.dotaautochessguide.screens.items.di.ItemsComponentHolder
import com.vladlen.dotaautochessguide.screens.items.di.ItemsFactory
import kotlinx.android.synthetic.main.fragment_items.*
import javax.inject.Inject

class ItemsFragment : BaseFragment() {

    companion object {
        fun newInstance(): ItemsFragment {
            val args = Bundle()
            val fragment = ItemsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var itemsFactory: ItemsFactory
    lateinit var itemsViewModel: ItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ItemsComponentHolder.instance.bindComponent(
            DaggerItemsComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        ItemsComponentHolder.instance.component?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        itemsViewModel = ViewModelProviders.of(this, itemsFactory).get(ItemsViewModel::class.java)

        rcl_items

        rcl_items.setHasFixedSize(true)
        rcl_items.layoutManager = GridLayoutManager(context, 4)
        rcl_items.addItemDecoration(ItemOffsetDecoration(context!!, R.dimen.item_offset))

        val itemsAdapter = ItemsAdapter(object : OnCellDelegateClickListener<IItem> {
            override fun onCellDelegateClick(item: IItem) {

            }
        })
        rcl_items.adapter = itemsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        ItemsComponentHolder.instance.unbindComponent()
    }
}