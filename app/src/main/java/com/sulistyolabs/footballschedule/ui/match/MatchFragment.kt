package com.sulistyolabs.footballschedule.ui.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_main.*

class MatchFragment : Fragment(){

    lateinit var pagesAdapter: FragmentPagerAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        pagesAdapter = FragmentPagerAdapter(childFragmentManager)
        pagesAdapter.addFragment(NextMatchFragment(), "Next Match")
        pagesAdapter.addFragment(LastMatchFragment(), "Last Match")
        pagerTab.adapter = pagesAdapter
        tab.setupWithViewPager(pagerTab)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }



}
