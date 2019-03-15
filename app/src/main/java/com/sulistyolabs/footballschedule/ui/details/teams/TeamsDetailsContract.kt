package com.sulistyolabs.footballschedule.ui.details.teams

import android.content.Context
import com.sulistyolabs.footballschedule.model.Team

interface TeamsDetailsContract {

    interface View {
        fun hideLoading()
        fun showLoading()
    }

    interface Presenter {
        fun addFavorite(data: Team?, context: Context)
        fun removeFavorite(id: String, context: Context)
        fun onDestroyPresenter()
    }
}