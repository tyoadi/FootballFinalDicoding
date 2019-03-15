package com.sulistyolabs.footballschedule.ui.favorite.team

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.footballschedule.db.FavoriteTeam
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Event
import com.sulistyolabs.footballschedule.model.Team
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavTeamPresenter(val mView: FavTeamContract.View) : FavTeamContract.Presenter {


    override fun getFootballTeamDb(context: Context) {

        mView.showLoading()
        context.database.use {
            select(FavoriteTeam.TABLE_FAVORITE).exec {
                val data = this.parseList(FavTeamPresenter.MyRowParserTeam())
                mView.hideLoading()
                mView.displayFavTeam(data)
            }
        }

    }

    class MyRowParserTeam : MapRowParser<Team> {
        override fun parseRow(columns: Map<String, Any?>): Team {
            val json: String? = columns[
                    FavoriteTeam.JSON_DATA].toString()
            return Gson().fromJson<Team>(json, Team::class.java)
        }
    }
}