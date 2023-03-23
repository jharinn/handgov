package com.myhand.nationalassembly.ui.view.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myhand.nationalassembly.data.remote.bill.publicapi.model.BillItem
import com.myhand.nationalassembly.databinding.ItemBillBinding

class MemberBillAdapter :
    ListAdapter<BillItem, MemberBillAdapter.MemberBillViewHolder>(ScheduleDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberBillViewHolder {
        return MemberBillViewHolder(
            ItemBillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemberBillViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    private var onItemClickListener: ((BillItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (BillItem) -> Unit) {
        onItemClickListener = listener
    }

    inner class MemberBillViewHolder(
        private val binding: ItemBillBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BillItem) {
            val title = item.billName
            val summary = item.summary
            val proposedDate = item.proposeDt
            val generalResult = item.generalResult
            val passDiv = item.passGubn

            itemView.apply {
                binding.tvBillTitle.text = title
                binding.tvMajorContent.text = summary
                binding.tvDateProposed.text = proposedDate
                binding.tvMeetingResult.text = generalResult
                binding.tvProcDiv.text = passDiv
            }
        }
    }

    companion object {
        private val ScheduleDiffCallback = object : DiffUtil.ItemCallback<BillItem>() {
            override fun areItemsTheSame(oldItem: BillItem, newItem: BillItem): Boolean {
                return oldItem.billId == newItem.billId
            }

            override fun areContentsTheSame(oldItem: BillItem, newItem: BillItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}