package com.sulistyolabs.footballschedule.ui.search

import com.google.gson.Gson
import com.sulistyolabs.footballschedule.model.EventSearch
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (val mView: SearchContract.ViewEvent,
                            val apiRepository: ApiRepository,
                            val gson: Gson) : SearchContract.PresenterEvent {


    override fun getQueryEvent(queryEvent: String) {
        mView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBSportApi.getSearchEvent(queryEvent))
                    .await(),
                    EventSearch::class.java
            )
            data.event.let {
                mView.showQueryEvent(it)
            }

            mView.hideLoading()
        }

    }
    override fun onDestroy() {

    }

}