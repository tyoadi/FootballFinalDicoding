package com.sulistyolabs.footballschedule.ui.favorite.match

import android.content.Context
import com.google.gson.Gson
import com.sulistyolabs.footballschedule.db.FavoriteMatch
import com.sulistyolabs.footballschedule.db.database
import com.sulistyolabs.footballschedule.model.Event
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

class FavMatchPresenter (val mView: FavMatchContract.View) : FavMatchContract.Presenter {

    override fun getFootballMatchData(context: Context) {
        mView.showLoading()
        context.database.use {
            select(FavoriteMatch.TABLE_FAVORITE).exec {
                val data = this.parseList(MyRowParserMatch())
                mView.hideLoading()
                mView.displayFavoriteMatch(data)
            }
        }
    }

    class MyRowParserMatch : MapRowParser<Event> {
        override fun parseRow(columns: Map<String, Any?>): Event {
            val json: String? = columns[
            FavoriteMatch.JSON_DATA].toString()
            return Gson().fromJson<Event>(json, Event::class.java)
        }
    }

}