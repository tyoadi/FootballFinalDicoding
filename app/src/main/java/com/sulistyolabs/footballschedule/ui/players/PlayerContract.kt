package com.sulistyolabs.footballschedule.ui.players

import com.sulistyolabs.footballschedule.model.Player

interface PlayerContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showListPlayer(data: List<Player>)
    }

    interface Presenter {
        fun getPlayerData(teamId: String = "4328")
        fun onDestroyPresenter()
    }
}