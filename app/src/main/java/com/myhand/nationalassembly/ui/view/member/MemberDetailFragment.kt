package com.myhand.nationalassembly.ui.view.member

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.myhand.nationalassembly.R
import com.myhand.nationalassembly.databinding.FragmentMemberDetailBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberDetailPagerAdapter
import com.myhand.nationalassembly.ui.view.member.adapter.MemberInfoItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MemberDetailFragment : Fragment(), View.OnClickListener {
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
        setClickListener()
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

    private fun setClickListener() {
        binding.itemMemberDetail.tvPhone.setOnClickListener(this)
        binding.itemMemberDetail.tvEmail.setOnClickListener(this)
        binding.itemMemberDetail.tvHomepage.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_phone -> {
                val intent =
                    Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:" + binding.itemMemberDetail.tvPhone.text.toString())
                    )
                startActivity(intent)
            }
            R.id.tv_email -> {
                val email = binding.itemMemberDetail.tvEmail.text.toString()
                val chooserTitle = "메일 보내기"

                val uri = Uri.parse("mailto:$email")
                    .buildUpon()
                    .build()

                val emailIntent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(Intent.createChooser(emailIntent, chooserTitle))
            }
            R.id.tv_homepage -> {
                var link = binding.itemMemberDetail.tvHomepage.text.toString()

                if (!link.startsWith("http"))
                    link = "http://$link"

                val action =
                    MemberDetailFragmentDirections.actionFragmentMemberDetailToWebViewFragment(link)
                findNavController().navigate(action)
            }
        }
    }
}