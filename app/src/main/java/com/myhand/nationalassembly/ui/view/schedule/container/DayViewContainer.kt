package com.myhand.nationalassembly.ui.view.schedule.container

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer
import com.myhand.nationalassembly.R

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
    val todayBg = view.findViewById<View>(R.id.calendarTodayDayDot)
    val selectedBg = view.findViewById<View>(R.id.calendarDayDot)

}