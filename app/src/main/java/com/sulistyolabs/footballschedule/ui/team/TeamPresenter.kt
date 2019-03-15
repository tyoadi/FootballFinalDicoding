package com.sulistyolabs.footballschedule.ui.team

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.Leaugues
import com.sulistyolabs.footballschedule.model.Teams
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(val mView: TeamContract.View?,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson): TeamContract.Presenter {

    override fun getLeagueData(footballLeage: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBSportApi.getAllLeagues(footballLeage))
                    .await(),
                    Leaugues::class.java
            )
            if (data.equals(null)) {
                mView?.onFailure()
            } else {
                mView?.showAllLeague(data)
            }
        }
    }

    override fun getTeamData(teamId: String) {
        mView?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBSportApi.getLookupAllTeam(teamId))
                    .await(),
                    Teams::class.java
            )
            if (data.equals(null)) {
                mView?.hideLoading()
                mView?.onFailure()
            } else {
                mView?.hideLoading()
                data.teams?.let { mView?.showListTeam(it) }
            }

        }
    }

    override fun onDestroyPresenter() {
    }

}