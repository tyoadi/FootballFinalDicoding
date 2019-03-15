package com.sulistyolabs.footballschedule.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(@SerializedName("strPlayer")
                      val strPlayer: String?,
                  @SerializedName("dateBorn")
                      val dateBorn: String?,
                  @SerializedName("strNationality")
                      val strNationality: String?,
                  @SerializedName("strSport")
                      val strSport: String?,
                  @SerializedName("strWeight")
                      val strWeight: String?,
                  @SerializedName("idTeam")
                      val idTeam: String?,
                  @SerializedName("strDescriptionEN")
                      val strDescriptionEN: String?,
                  @SerializedName("strHeight")
                      val strHeight: String?,
                  @SerializedName("strPosition")
                      val strPosition: String?,
                  @SerializedName("strCutout")
                      val strCutout: String?,
                  @SerializedName("idPlayerManager")
                      val idPlayerManager: String?,
                  @SerializedName("strLocked")
                      val strLocked: String?,
                  @SerializedName("idSoccerXML")
                      val idSoccerXML: String?,
                  @SerializedName("strTeam")
                      val strTeam: String?,
                  @SerializedName("intSoccerXMLTeamID")
                      val intSoccerXMLTeamID: String?,
                  @SerializedName("strSigning")
                      val strSigning: String?,
                  @SerializedName("idPlayer")
                      val idPlayer: String?,
                  @SerializedName("strThumb")
                      val strThumb: String?,
                  @SerializedName("dateSigned")
                      val dateSigned: String?) : Parcelable