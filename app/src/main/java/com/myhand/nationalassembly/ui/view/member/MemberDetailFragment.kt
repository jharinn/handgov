package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.myhand.nationalassembly.databinding.FragmentMemberDetailBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberDetailPagerAdapter
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberDetailFragment : Fragment() {
    private var _binding: FragmentMemberDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager: ViewPager2
    private val args by navArgs<MemberDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = binding.vpMemberDetail
        val tabLayout = binding.tabMemberDetail
        val infoItem = args.memberDetailInfo
        setInfo(infoItem)
        setHasOptionsMenu(true)
        val adapter = MemberDetailPagerAdapter(this, infoItem)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "상세정보"
                }
                1 -> {
                    tab.text = "발의법안"
                }
            }
        }.attach()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setInfo(infoItem: MemberInfoItem) {
        binding.itemMemberDetail.apply {
            tvName.text = infoItem.name
            tvParty.text = infoItem.polyName
            tvLocal.text = infoItem.electLocalName
            tvPhone.text = infoItem.telNo
            tvEmail.text = infoItem.eMail
            tvHomepage.text = infoItem.homepage
        }

        Glide.with(this)
            .load(infoItem.photoLink)
            .into(binding.itemMemberDetail.ivMemberPhoto)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}