package com.myhand.nationalassembly.ui.view.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myhand.nationalassembly.databinding.ItemScheduleBinding

class SchedulePagingAdapter :
    PagingDataAdapter<ScheduleItem, SchedulePagingAdapter.ScheduleViewHolder>(
        MemberInfoDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val memberItem = getItem(position)

        memberItem?.let { member ->
            holder.bind(member)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(member) }
            }
        }
    }

    private var onItemClickListener: ((ScheduleItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ScheduleItem) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val MemberInfoDiffCallback = object : DiffUtil.ItemCallback<ScheduleItem>() {
            override fun areItemsTheSame(
                oldItem: ScheduleItem,
                newItem: ScheduleItem
            ): Boolean {
                return oldItem.link == newItem.link
            }

            override fun areContentsTheSame(
                oldItem: ScheduleItem,
                newItem: ScheduleItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ScheduleViewHolder(
        private val binding: ItemScheduleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(memberItem: ScheduleItem) {
            val title = memberItem.title
            val description = memberItem.description
            val dateTime = memberItem.sDate + memberItem.sTime
            val place = memberItem.location

            itemView.apply {
                binding.tvTitle.text = title
                binding.tvDescription.text = description
                binding.tvDateTime.text = dateTime
                binding.tvPlace.text = place
            }
        }
    }
}