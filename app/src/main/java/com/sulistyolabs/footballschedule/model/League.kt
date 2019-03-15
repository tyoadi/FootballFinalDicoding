package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName

data class League(@SerializedName("strLeagueAlternate")
                       val strLeagueAlternate: String = "",
                  @SerializedName("strLeague")
                       val strLeague: String = "",
                  @SerializedName("strSport")
                       val strSport: String = "",
                  @SerializedName("idLeague")
                       val idLeague: String = "")