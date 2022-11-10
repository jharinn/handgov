package com.myhand.nationalassembly.ui.view.schedule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myhand.nationalassembly.databinding.ItemScheduleBinding

class ScheduleAdapter :
    ListAdapter<ScheduleItem, ScheduleAdapter.ScheduleViewHolder>(ScheduleDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        return ScheduleViewHolder(
            ItemScheduleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val book = currentList[position]
        holder.bind(book)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(book) }
        }
    }

    private var onItemClickListener: ((ScheduleItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ScheduleItem) -> Unit) {
        onItemClickListener = listener
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

    companion object {
        private val ScheduleDiffCallback = object : DiffUtil.ItemCallback<ScheduleItem>() {
            override fun areItemsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
                return oldItem.description == newItem.description
            }

            override fun areContentsTheSame(oldItem: ScheduleItem, newItem: ScheduleItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}