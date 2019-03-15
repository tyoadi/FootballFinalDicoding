package com.sulistyolabs.footballschedule.ui.details.matches

import android.content.Context
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Team

interface MatchDetailsContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayHomeTeamBadge(team: List<Team>?)
        fun displayAwayTeamBadge(team: List<Team>?)
    }

    interface Presenter {
        fun getHomeTeamsBadge(id: String)
        fun getAwayTeamsBadge(id: String)
        fun addFavorite(data: Event?, context: Context)
        fun removeFavorite(id: String, context: Context)
        fun onDestroyPresenter()
    }
}