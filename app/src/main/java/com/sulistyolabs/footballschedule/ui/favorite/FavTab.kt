package com.sulistyolabs.footballschedule.ui.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.FragmentPagerAdapter
import com.sulistyolabs.footballschedule.ui.favorite.match.FavMatch
import com.sulistyolabs.footballschedule.ui.favorite.team.FavTeam
import kotlinx.android.synthetic.main.fragment_fav_tab.*


class FavTab : Fragment() {

    lateinit var pagesAdapter: FragmentPagerAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagesAdapter = FragmentPagerAdapter(childFragmentManager)
        pagesAdapter.addFragment(FavMatch(),"Match")
        pagesAdapter.addFragment(FavTeam(),"Team")
        pagerTab.adapter = pagesAdapter
        tab.setupWithViewPager(pagerTab)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_tab, container, false)
    }


}
