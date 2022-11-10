package com.myhand.nationalassembly.ui.view.schedule.container

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer
import com.myhand.nationalassembly.R

class MonthViewContainer(view: View) : ViewContainer(view) {
    val monthTitle = view.findViewById<TextView>(R.id.month_title)
    val dayTitlesContianer = view.findViewById<LinearLayout>(R.id.linear_day_titles)
}
