package com.sulistyolabs.footballschedule.ui.players


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.PlayersAdapter
import com.sulistyolabs.footballschedule.model.Player
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerFragment : Fragment(), PlayerContract.View {

    private var players: MutableList<Player> = mutableListOf()
    private lateinit var mPresnter: PlayerPresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val gson = Gson()
        val request = ApiRepository()

        mPresnter = PlayerPresenter(this, request, gson)

        if (arguments != null) {
            val team: Team? = arguments?.getParcelable("team")
            team?.idTeam?.let { mPresnter.getPlayerData(it) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun hideLoading() {
        progressPlayer.invisible()
    }

    override fun showLoading() {
        progressPlayer.visible()
    }

    override fun showListPlayer(data: List<Player>) {

        players.clear()
        players.addAll(data)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recylePlayer.layoutManager = layoutManager
        recylePlayer.adapter = PlayersAdapter(data, context)

    }
}
