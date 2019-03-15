package com.sulistyolabs.footballschedule.ui.search

import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Team

interface SearchContract {

    interface ViewEvent {
        fun showLoading()
        fun hideLoading()
        fun showQueryEvent(data: List<Event>)
    }

    interface PresenterEvent {
        fun getQueryEvent(queryEvent: String = "")
        fun onDestroy()
    }

    interface ViewTeam {
        fun showLoading()
        fun hideLoading()
        fun showQueryTeam(data: List<Team>)

    }

    interface PresenterTeam {
        fun getQueryTeam(queryTeam: String = "")
        fun onDestroy()
    }

}