package com.sulistyolabs.footballschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(@SerializedName("intStadiumCapacity")
                     val intStadiumCapacity: String? = "",
                @SerializedName("strTeamShort")
                     val strTeamShort: String?,
                @SerializedName("strSport")
                     val strSport: String? = "",
                @SerializedName("strTeamJersey")
                     val strTeamJersey: String? = "",
                @SerializedName("strStadiumDescription")
                     val strStadiumDescription: String? = "",
                @SerializedName("strTeamFanart1")
                     val strTeamFanart: String? = "",
                @SerializedName("intLoved")
                     val intLoved: String?,
                @SerializedName("idLeague")
                     val idLeague: String? = "",
                @SerializedName("idSoccerXML")
                     val idSoccerXML: String? = "",
                @SerializedName("strTeamLogo")
                     val strTeamLogo: String? = "",
                @SerializedName("strStadiumLocation")
                     val strStadiumLocation: String? = "",
                @SerializedName("strCountry")
                     val strCountry: String? = "",
                @SerializedName("strRSS")
                     val strRSS: String? = "",
                @SerializedName("strTeamBanner")
                     val strTeamBanner: String? = "",
                @SerializedName("strStadiumThumb")
                     val strStadiumThumb: String? = "",
                @SerializedName("intFormedYear")
                     val intFormedYear: String? = "",
                @SerializedName("idTeam")
                     val idTeam: String = "",
                @SerializedName("strDescriptionEN")
                     val strDescriptionEN: String?,
                @SerializedName("strLocked")
                     val strLocked: String? = "",
                @SerializedName("strAlternate")
                     val strAlternate: String? = "",
                @SerializedName("strTeam")
                     val strTeam: String? = "",
                @SerializedName("strGender")
                     val strGender: String? = "",
                @SerializedName("strStadium")
                     val strStadium: String? = "",
                @SerializedName("strTeamBadge")
                     val strTeamBadge: String? = "",
                @SerializedName("strLeague")
                     val strLeague: String? = "",
                @SerializedName("strManager")
                     val strManager: String? = "") : Parcelable