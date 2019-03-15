package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName

data class Players(@SerializedName("player")
                   val player: List<Player>?)