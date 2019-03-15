package com.sulistyolabs.footballschedule.ui.players

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.Players
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(val mView: PlayerContract.View,
                      val apiRepository: ApiRepository,
                      val gson: Gson): PlayerContract.Presenter {

    override fun getPlayerData(teamId: String) {
        mView.showLoading()
       GlobalScope.launch(Dispatchers.Main) {
           val data = gson.fromJson(apiRepository
                   .doRequest(DBSportApi.getAllPlayer(teamId))
                   .await(),
                   Players::class.java
           )
           data.player?.let {
               mView.showListPlayer(it)
           }
           mView.hideLoading()
       }

    }

    override fun onDestroyPresenter() {

    }
}