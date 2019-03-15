package com.sulistyolabs.footballschedule.model

import com.google.gson.annotations.SerializedName

data class Leaugues(@SerializedName("leagues")
                    val leagues: List<League>?)