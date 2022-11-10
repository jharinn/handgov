package com.myhand.nationalassembly.ui.view.report.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReportItem(
    var title: String? = null,
    var link: String? = null,
    var regDate: String? = null
) : Parcelable