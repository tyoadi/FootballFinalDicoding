package com.sulistyolabs.footballschedule.ui.team


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson

import com.sulistyolabs.footballschedule.R
import com.sulistyolabs.footballschedule.adapter.TeamsAdapter
import com.sulistyolabs.footballschedule.model.Leaugues
import com.sulistyolabs.footballschedule.model.Team
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.utils.invisible
import com.sulistyolabs.footballschedule.utils.visible
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.toast

class TeamFragment : Fragment(), TeamContract.View {


    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var  mPresenter: TeamPresenter
    val leaugeSoccer: String = "Soccer"


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val gson = Gson()
        val request = ApiRepository()
        mPresenter = TeamPresenter(this, request, gson)
        mPresenter.getLeagueData(leaugeSoccer)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun showAllLeague(data: Leaugues?) {
        val spinnerItem = data?.leagues?.map {
            it.strLeague
        }

        val spinnerId = data?.leagues?.map {
            it.idLeague
        }

        spinnerTeam?.adapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem)
        spinnerTeam?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerId?.get(position)?.let { mPresenter.getTeamData(it) }

                swipeTeam?.setOnRefreshListener {
                    spinnerId?.get(position)?.let { mPresenter.getTeamData(it) }
                }
            }

        }

    }

    override fun hideLoading() {
        progressTeam?.invisible()

    }

    override fun showLoading() {
        progressTeam?.visible()
    }

    override fun showListTeam(data: List<Team>) {
        swipeTeam?.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycleTeam?.layoutManager = layoutManager
        recycleTeam?.adapter = TeamsAdapter(data, context)

    }

    override fun onFailure() {
        toast("Failed to get data, cek your internet connection and refress")
    }
}
