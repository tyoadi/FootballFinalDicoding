package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import java.util.Date


@Parcelize
class Event(@SerializedName("idEvent")
            val idEvent: String,
            @SerializedName("strEvent")
            var strEvent: String?,
            @SerializedName("strSport")
            var strSport: String?,
            @SerializedName("dateEvent")
            var dateEvent: Date?,
            @SerializedName("strHomeTeam")
            val strHomeTeam: String,
            @SerializedName("strAwayTeam")
            val strAwayTeam: String,
            @SerializedName("intHomeScore")
            val intHomeScore: String?,
            @SerializedName("intAwayScore")
            val intAwayScore: String?,
            @SerializedName("strHomeGoalDetails")
            val strHomeGoalDetails: String?,
            @SerializedName("strHomeLineupDefense")
            val strHomeLineupDefense: String?,
            @SerializedName("strHomeLineupMidfield")
            val strHomeLineupMidfield: String?,
            @SerializedName("strHomeLineupForward")
            val strHomeLineupForward: String?,
            @SerializedName("strHomeLineupSubstitutes")
            val strHomeLineupSubstitutes: String?,
            @SerializedName("strAwayGoalDetails")
            val strAwayGoalDetails: String?,
            @SerializedName("strAwayLineupDefense")
            val strAwayLineupDefense: String?,
            @SerializedName("strAwayLineupMidfield")
            val strAwayLineupMidfield: String?,
            @SerializedName("strAwayLineupForward")
            val strAwayLineupForward: String?,
            @SerializedName("strAwayLineupSubstitutes")
            val strAwayLineupSubstitutes: String?,
            @SerializedName("strTime")
            val strTime: String?,
            @SerializedName("idHomeTeam")
            val idHomeTeam: String = "",
            @SerializedName("idAwayTeam")
            val idAwayTeam: String = "") : Parcelable