package com.myhand.nationalassembly.ui.view.report.adapter

import androidx.recyclerview.widget.DiffUtil

object DiffCallback {
    val ReportDiffCallback = object : DiffUtil.ItemCallback<ReportItem>() {
        override fun areItemsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ReportItem, newItem: ReportItem): Boolean {
            return oldItem == newItem
        }
    }
}