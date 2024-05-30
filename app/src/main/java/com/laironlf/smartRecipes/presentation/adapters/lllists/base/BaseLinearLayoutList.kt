package com.laironlf.smartRecipes.presentation.adapters.lllists.base

import android.view.View
import android.view.ViewGroup

abstract class BaseLinearLayoutList<VH>(
    private val parent: ViewGroup
) where VH: BaseLinearLayoutList.ViewHolder {
    abstract fun getItemCount(): Int
    abstract fun onBindViewHolder(holder: VH, position: Int)
    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    fun notifyDataSetChanged() {
        parent.removeAllViews()
        for (i in 0..< getItemCount()){
            val viewHolder = onCreateViewHolder(parent)
            onBindViewHolder(viewHolder, i)
            parent.addView(viewHolder.itemView)
        }
    }
    abstract class ViewHolder(val itemView: View)

}