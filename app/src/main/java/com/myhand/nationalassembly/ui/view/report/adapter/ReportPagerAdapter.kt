package com.myhand.nationalassembly.ui.view.report.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myhand.nationalassembly.ui.view.report.BudgetFragment
import com.myhand.nationalassembly.ui.view.report.ReportFragment

class ReportPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ReportFragment()
            1 -> return BudgetFragment()
        }
        return ReportFragment()
    }
}