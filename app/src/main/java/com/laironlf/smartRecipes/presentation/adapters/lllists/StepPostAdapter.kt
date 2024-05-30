package com.laironlf.smartRecipes.presentation.adapters.lllists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.post.StepPost
import com.laironlf.smartRecipes.presentation.adapters.lllists.base.BaseLinearLayoutList
import com.squareup.picasso.Picasso
import java.io.File

class StepPostAdapter(
    parent: ViewGroup
) : BaseLinearLayoutList<StepPostAdapter.ViewHolder>(parent) {

    var steps: List<StepPost> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount(): Int = steps.size

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_step, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val step = steps[position]
        holder.tvText.text = step.text
        holder.tvStep.text = (position + 1).toString()
        holder.setImage(step.imageUrl)
    }

    class ViewHolder(itemView: View) : BaseLinearLayoutList.ViewHolder(itemView) {
        val tvText: TextView = itemView.findViewById(R.id.tv_stepText)
        val tvStep: TextView = itemView.findViewById(R.id.tv_stepNum)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_stepImage)
        private val cvImageContainer: CardView = itemView.findViewById(R.id.cv_imageContainer)
        fun setImage(url: String?){
            if (url == null || url == ""){
                cvImageContainer.visibility = View.GONE
                return
            }

            Picasso.get()
                .load(File(url))
                .into(ivImage)
        }
    }
}