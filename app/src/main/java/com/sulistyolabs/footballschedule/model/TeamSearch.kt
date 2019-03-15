package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName

data class TeamSearch (
        @SerializedName("team")
        val team: List<Team>
)