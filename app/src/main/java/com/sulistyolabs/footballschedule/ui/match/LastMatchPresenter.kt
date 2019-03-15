package com.sulistyolabs.footballschedule.ui.match

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.Events
import com.sulistyolabs.footballschedule.model.Leaugues
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LastMatchPresenter(val mView: MatchContract.View?,
                         private  val apiRepositoryRepo: ApiRepository,
                         private val gson: Gson): MatchContract.Presenter {

    override fun getLeagueData(footballLeage: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepositoryRepo
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

    override fun getMatchData(matchId: String) {
        mView?.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepositoryRepo
                    .doRequest(DBSportApi.getLastMatch(matchId))
                    .await(),
                    Events::class.java
            )
            if (data.equals(null)) {
                mView?.hideLoading()
                mView?.onFailure()
            } else {
                mView?.hideLoading()
                data.events?.let { mView?.showListEvent(it) }
            }

        }

    }

    override fun onDestroyPresenter() {

    }
}