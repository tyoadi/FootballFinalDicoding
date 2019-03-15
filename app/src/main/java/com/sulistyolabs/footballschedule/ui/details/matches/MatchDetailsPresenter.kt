package com.sulistyolabs.footballschedule.ui.details.matches

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.footballschedule.db.FavoriteMatch
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Teams
import com.sulistyolabs.footballschedule.rest.ApiRepository
import com.sulistyolabs.footballschedule.rest.DBSportApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert

class MatchDetailsPresenter(val mView: MatchDetailsContract.View,
                            private val apiRepositoryRepo: ApiRepository,
                            private val gson: Gson) : MatchDetailsContract.Presenter {

    override fun addFavorite(data: Event?, context: Context) {
        val gson = Gson()
        val json = gson.toJson(data)
        context.database.use {
            insert(FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.MATCH_ID to data?.idEvent,
                    FavoriteMatch.JSON_DATA to json)
        }

    }

    override fun removeFavorite(id: String, context: Context) {
        context.database.use {
            delete(FavoriteMatch.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to id)
        }
    }

    override fun getHomeTeamsBadge(id: String) {
        mView.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepositoryRepo.doRequest(DBSportApi.getLookupTeam(id))
                    .await(),
                    Teams::class.java
            )
            mView.hideLoading()
            mView.displayHomeTeamBadge(data.teams)
        }
    }

    override fun getAwayTeamsBadge(id: String) {
       GlobalScope.launch(Dispatchers.Main) {
           val data = gson.fromJson(apiRepositoryRepo.doRequest(DBSportApi.getLookupTeam(id))
                   .await(),
                   Teams::class.java
           )
           mView.displayAwayTeamBadge(data.teams)
       }
    }

    override fun onDestroyPresenter() {

    }

}