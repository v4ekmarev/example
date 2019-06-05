package com.vladlen.dotaautochessguide.screens.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.vladlen.dotaautochessguide.R
import com.vladlen.dotaautochessguide.screens.guides.GuidesFragment
import com.vladlen.dotaautochessguide.screens.items.ItemsFragment
import com.vladlen.dotaautochessguide.screens.statistics.StatisticsFragment
import com.vladlen.dotaautochessguide.screens.tierlist.TierListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_guides -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, GuidesFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tier_list -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, TierListFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_items -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, ItemsFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_statistics -> {
                supportFragmentManager.beginTransaction().replace(R.id.container, StatisticsFragment.newInstance()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
