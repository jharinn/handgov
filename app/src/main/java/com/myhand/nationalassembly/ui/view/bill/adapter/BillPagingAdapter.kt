package com.myhand.nationalassembly.ui.view.bill.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myhand.nationalassembly.data.remote.bill.openapi.model.BillLinkRow
import com.myhand.nationalassembly.databinding.ItemBillLinkBinding


class BillPagingAdapter :
    PagingDataAdapter<BillLinkRow, BillPagingAdapter.BillViewHolder>(BillDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        return BillViewHolder(
            ItemBillLinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    private var onItemClickListener: ((BillLinkRow) -> Unit)? = null
    fun setOnItemClickListener(listener: (BillLinkRow) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val BillDiffCallback = object : DiffUtil.ItemCallback<BillLinkRow>() {
            override fun areItemsTheSame(oldItem: BillLinkRow, newItem: BillLinkRow): Boolean {
                return oldItem.billId == newItem.billId
            }

            override fun areContentsTheSame(oldItem: BillLinkRow, newItem: BillLinkRow): Boolean {
                return oldItem == newItem
            }
        }
    }


    inner class BillViewHolder(
        private val binding: ItemBillLinkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BillLinkRow) {
            val title = item.billName
            val proposedDate = item.propose_dt
            val generalResult = item.procResult
            val proposer = item.proposer
            val publProposer = item.publProposer

            itemView.apply {
                binding.tvBillTitle.text = title
                binding.tvDateProposed.text = proposedDate
                binding.tvMeetingResult.text = generalResult
                binding.tvProposer.text = proposer
                binding.tvCoProposer.text = publProposer
            }
        }
    }
}
