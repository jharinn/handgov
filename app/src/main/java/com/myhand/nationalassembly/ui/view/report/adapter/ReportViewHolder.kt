package com.myhand.nationalassembly.ui.view.report.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myhand.nationalassembly.databinding.ItemReportBinding

class ReportViewHolder(
    private val binding: ItemReportBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReportItem) {
        val title = item.title
        val regDate = item.regDate

        itemView.apply {
            binding.tvReportTitle.text = title
            binding.tvRegDate.text = regDate
        }
    }
}