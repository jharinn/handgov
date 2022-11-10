package com.myhand.nationalassembly.ui.view.report.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.myhand.nationalassembly.databinding.ItemReportBinding

class ReportPagingAdapter :
    PagingDataAdapter<ReportItem, ReportViewHolder>(DiffCallback.ReportDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        return ReportViewHolder(
            ItemReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    private var onItemClickListener: ((ReportItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ReportItem) -> Unit) {
        onItemClickListener = listener
    }
}