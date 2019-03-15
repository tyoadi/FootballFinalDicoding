package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName

data class EventSearch (
        @SerializedName("event")
        val event: List<Event>
)

