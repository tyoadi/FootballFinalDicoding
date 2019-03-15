package com.sulistyolabs.footballschedule.ui.favorite.match

import android.content.Context
import com.sulistyolabs.footballschedule.model.Event

interface FavMatchContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun displayFavoriteMatch(matchList: List<Event>)
    }

    interface Presenter {
        fun getFootballMatchData(context: Context)
    }
}