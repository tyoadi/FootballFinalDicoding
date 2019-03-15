package com.sulistyolabs.footballschedule.ui.favorite.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.TeamsAdapter
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_fav_team.*


class FavTeam : Fragment(), FavTeamContract.View {

    private var listFavTeam: MutableList<Team> = mutableListOf()
    private lateinit var mPresenter: FavTeamPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mPresenter = FavTeamPresenter(this)
        swiperefreshFavTeam.setOnClickListener {
            mPresenter.getFootballTeamDb(requireContext())
            hideLoading()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_team, container, false)
    }

    override fun hideLoading() {
        progressFavTeam.invisible()

    }

    override fun showLoading() {
        progressFavTeam.visible()

    }

    override fun onResume() {
        super.onResume()
        mPresenter.getFootballTeamDb(requireContext())
    }

    override fun displayFavTeam(teamList: List<Team>) {
        swiperefreshFavTeam.isRefreshing = false
        listFavTeam.clear()
        listFavTeam.addAll(teamList)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleFavTeam.layoutManager = layoutManager
        recycleFavTeam.adapter = TeamsAdapter(teamList, context)

    }


}
