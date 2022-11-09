package com.myhand.nationalassembly.ui.view.member.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.myhand.nationalassembly.ui.view.member.MemberBillFragment
import com.myhand.nationalassembly.ui.view.member.MemberInfoFragment

class MemberDetailPagerAdapter(fragment: Fragment, val infoItem: MemberInfoItem) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return MemberInfoFragment(infoItem)
            1 -> return MemberBillFragment(infoItem)
        }
        return MemberInfoFragment(infoItem)
    }
}