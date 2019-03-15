package com.sulistyolabs.footballschedule.ui.match

import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Leaugues

interface MatchContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showListEvent(data: List<Event>)
        fun showAllLeague(data: Leaugues?)
        fun onFailure()

    }

    interface Presenter {
        fun getMatchData(matchId: String = "4328")
        fun getLeagueData(footballLeage: String = "Soccer")
        fun onDestroyPresenter()
    }
}