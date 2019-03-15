package com.sulistyolabs.footballschedule.ui.details.teams

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.footballschedule.db.FavoriteTeam
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Team
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert


class TeamsDetailsPresenter(val mView: TeamsDetailsContract.View): TeamsDetailsContract.Presenter {


    override fun addFavorite(data: Team?, context: Context) {
        val gson = Gson()
        val json = gson.toJson(data)
        context.database.use {
            insert(FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.TEAM_ID to data?.idTeam,
                    FavoriteTeam.JSON_DATA to json)
        }
    }

    override fun removeFavorite(id: String, context: Context) {
        context.database.use {
            delete(FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {id})", "id" to id)
        }

    }

    override fun onDestroyPresenter() {

    }
}