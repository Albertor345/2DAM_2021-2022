package com.example.seriespelisretrofit.ui.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CapituloUI(val episodeNumber: Int?,
                      val id: Int?,
                      val name: String?) : Parcelable