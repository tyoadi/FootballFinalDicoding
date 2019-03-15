package com.sulistyolabs.footballschedule.rest

import android.net.Uri
import com.sulistyolabs.footballschedule.BuildConfig

object DBSportApi {

    fun getLastMatch(leauge: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", leauge)
                .build()
                .toString()
    }
    fun getNextMatch(leauge: String?): String  {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", leauge)
                .build()
                .toString()
    }
    fun getLookupTeam(teamId: String?): String  {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
    }

    fun getLookupAllTeam(teamId: String?): String  {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookup_all_teams.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
    }



    fun getAllLeagues(leaugeFootball: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("all_leagues.php")
                .appendQueryParameter("s", leaugeFootball)
                .build()
                .toString()

    }

    fun getAllPlayer(teamId: String?): String  {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookup_all_players.php")
                .appendQueryParameter("id", teamId)
                .build()
                .toString()
    }

    fun getSearchEvent(queryE: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" + queryE
    }

    fun getSearchTeam(queryE: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + queryE
    }
}