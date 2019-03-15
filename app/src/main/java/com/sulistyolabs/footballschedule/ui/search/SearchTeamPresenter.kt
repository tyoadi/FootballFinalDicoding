package com.sulistyolabs.footballschedule.ui.search

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.Teams
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeamPresenter(val mView: SearchContract.ViewTeam,
                          val apiRepository: ApiRepository,
                          val gson: Gson) : SearchContract.PresenterTeam {

    override fun getQueryTeam(queryTeam: String) {
        mView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBSportApi.getSearchTeam(queryTeam))
                    .await(),
                    Teams::class.java
            )
            mView.hideLoading()
            data.teams?.let {
                mView.showQueryTeam(it)
            }
        }
    }

    override fun onDestroy() {

    }
}