package com.laironlf.smartRecipes.presentation.adapters.lllists

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.RecipeStep
import com.squareup.picasso.Picasso

class RecipeStepAdapter(
    private val parent: ViewGroup
) {

    var steps: List<RecipeStep> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private fun notifyDataSetChanged() {
        parent.removeAllViews()
        for (i in 0..< getItemCount()){
            val viewHolder = createViewHolder()
            Log.d(TAG, "notifyDataSetChanged: ${viewHolder == null}")
            bindViewHolder(viewHolder, i)
            parent.addView(viewHolder.itemView)
        }
    }

    private fun getItemCount() = steps.size

    private fun bindViewHolder(holder: ViewHolder, position: Int) {
        val step = steps[position]
        holder.tvText.text = step.text
        holder.tvStep.text = step.step.toString()
        holder.setImage(step.image)
    }

    private fun createViewHolder(): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_step, parent, false)
        Log.d(TAG, "createViewHolder: ${view == null}")
        return ViewHolder(view)
    }


    private class ViewHolder(val itemView: View){
        val tvText: TextView = itemView.findViewById(R.id.tv_stepText)
        val tvStep: TextView = itemView.findViewById(R.id.tv_stepNum)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_stepImage)
        private val cvImageContainer: CardView = itemView.findViewById(R.id.cv_imageContainer)
        fun setImage(url: String?){
            if (url == null){
                cvImageContainer.visibility = View.GONE
                return
            }
            Picasso.get()
                .load(url)
                .into(ivImage)
        }
    }

}