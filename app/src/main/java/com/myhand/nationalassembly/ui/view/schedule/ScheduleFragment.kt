package com.myhand.nationalassembly.ui.view.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.MonthHeaderFooterBinder
import com.myhand.nationalassembly.databinding.FragmentScheduleBinding
import com.myhand.nationalassembly.ui.view.schedule.adapter.ScheduleAdapter
import com.myhand.nationalassembly.ui.view.schedule.container.DayViewContainer
import com.myhand.nationalassembly.ui.view.schedule.container.MonthViewContainer
import com.myhand.nationalassembly.ui.view.schedule.viewmodel.ScheduleViewModel
import com.myhand.nationalassembly.util.LogUtil
import dagger.hilt.android.AndroidEntryPoint
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*

@AndroidEntryPoint
class ScheduleFragment : Fragment() {
    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<ScheduleViewModel>()

    private lateinit var scheduleAdapter: ScheduleAdapter
    var prevSelectedDate: DayViewContainer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewmodel = vm
        binding.lifecycleOwner = this

        setCalendar()
        setUpRecyclerView()

        fetchTodayList()
        vm._fetchScheduleResult.observe(viewLifecycleOwner) { result ->
            LogUtil.d("result: $result")
            scheduleAdapter.submitList(result)

            if (result.isEmpty())
                binding.tvNoData.visibility = View.VISIBLE
            else
                binding.tvNoData.visibility = View.GONE
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun fetchTodayList() {
        vm.getSchedules(
            date = LocalDate.now().dayOfMonth.toString(),
            month = LocalDate.now().monthValue.toString(),
            year = LocalDate.now().year.toString()
        )
    }

    private fun setUpRecyclerView() {
        scheduleAdapter = ScheduleAdapter()

        binding.rvSchedule.apply {
            adapter = scheduleAdapter
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        //TODO webview
        scheduleAdapter.setOnItemClickListener {

        }
    }

    fun setCalendar() {
        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.textView.text = data.date.dayOfMonth.toString()

                // 오늘 날짜 표시
                if (container.textView.text == LocalDate.now().dayOfMonth.toString()
                    && data.date.monthValue == LocalDate.now().monthValue
                    && data.date.year == LocalDate.now().year
                )
                    container.todayBg.visibility = View.VISIBLE

                container.textView.setOnClickListener {
                    LogUtil.d("click!! ${it.id} // date: ${data.date.year}.${data.date.monthValue}.${data.date.dayOfMonth}")

                    vm.getSchedules(
                        date = data.date.dayOfMonth.toString(),
                        month = data.date.monthValue.toString(),
                        year = data.date.year.toString()
                    )
                    if (prevSelectedDate != null) {
                        prevSelectedDate?.selectedBg?.visibility = View.GONE
                    }
                    prevSelectedDate = container
                    container.selectedBg.visibility = View.VISIBLE
                }
            }
        }
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)  // Adjust as needed
        val endMonth = currentMonth.plusMonths(100)  // Adjust as needed

        val daysOfWeek = daysOfWeek(firstDayOfWeek = DayOfWeek.MONDAY)
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)

        // day of week title
        binding.calendarView.monthHeaderBinder = object :
            MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, data: CalendarMonth) {
                if (container.dayTitlesContianer.tag == null) {
                    container.dayTitlesContianer.tag = data.yearMonth
                    container.dayTitlesContianer.children.map { it as TextView }
                        .forEachIndexed { index, textView ->
                            val dayOfWeek = daysOfWeek[index]
                            val title =
                                dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
                            textView.text = title
                        }

                    container.monthTitle.text = data.yearMonth.displayText()
                }
            }
        }
    }

    fun YearMonth.displayText(short: Boolean = false): String {
        return "${this.month.displayText(short = short)} ${this.year}"
    }

    fun Month.displayText(short: Boolean = true): String {
        val style = if (short) TextStyle.SHORT else TextStyle.FULL
        return getDisplayName(style, Locale.getDefault())
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}