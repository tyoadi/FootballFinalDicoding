package com.sulistyolabs.footballschedule.ui.team

import com.sulistyolabs.footballschedule.model.Leaugues
import com.sulistyolabs.footballschedule.model.Team

interface TeamContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showListTeam(data: List<Team>)
        fun showAllLeague(data: Leaugues?)
        fun onFailure()
    }

    interface Presenter {
        fun getTeamData(teamId: String = "4328")
        fun onDestroyPresenter()
        fun getLeagueData(footballLeage: String = "Soccer")
    }
}