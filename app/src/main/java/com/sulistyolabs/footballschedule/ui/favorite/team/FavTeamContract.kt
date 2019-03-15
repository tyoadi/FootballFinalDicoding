package com.sulistyolabs.footballschedule.ui.favorite.team

import android.content.Context
import com.sulistyolabs.footballschedule.model.Team

interface FavTeamContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFavTeam(teamList: List<Team>)
    }

    interface Presenter {
        fun getFootballTeamDb(context: Context)
    }
}