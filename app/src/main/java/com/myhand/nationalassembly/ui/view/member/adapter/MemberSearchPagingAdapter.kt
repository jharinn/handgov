package com.myhand.nationalassembly.ui.view.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myhand.nationalassembly.databinding.ItemMemberBinding
import com.myhand.nationalassembly.util.LogUtil

class MemberSearchPagingAdapter :
    PagingDataAdapter<MemberInfoItem, MemberSearchPagingAdapter.MemberSearchViewHolder>(
        MemberInfoDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberSearchViewHolder {
        return MemberSearchViewHolder(
            ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemberSearchViewHolder, position: Int) {
        val memberItem = getItem(position)

        memberItem?.let { member ->
            holder.bind(member)
            LogUtil.d("onBindViewHolder:: ${member.photoLink}")
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(member) }
            }
        }
    }

    private var onItemClickListener: ((MemberInfoItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (MemberInfoItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val MemberInfoDiffCallback = object : DiffUtil.ItemCallback<MemberInfoItem>() {
            override fun areItemsTheSame(
                oldItem: MemberInfoItem,
                newItem: MemberInfoItem
            ): Boolean {
                return oldItem.monaCode == newItem.monaCode
            }

            override fun areContentsTheSame(
                oldItem: MemberInfoItem,
                newItem: MemberInfoItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class MemberSearchViewHolder(
        private val binding: ItemMemberBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(memberItem: MemberInfoItem) {
            val name = memberItem.name
            val party = memberItem.polyName
            val local = memberItem.electLocalName

            itemView.apply {
                binding.tvName.text = name
                binding.tvParty.text = party
                binding.tvLocal.text = local
            }

            Glide.with(itemView)
                .load(memberItem.photoLink)
                .into(binding.ivMemberPhoto)
        }
    }
}