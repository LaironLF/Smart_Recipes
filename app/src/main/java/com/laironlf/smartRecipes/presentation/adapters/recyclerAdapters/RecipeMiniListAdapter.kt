package com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.Recipe
import com.squareup.picasso.Picasso

class RecipeMiniListAdapter(val onRecipeClick: (recipe: Recipe) -> Unit): RecyclerView.Adapter<RecipeMiniListAdapter.ViewHolder>() {


    var items: List<Recipe> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_mini, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]
        holder.itemView.setOnClickListener{onRecipeClick(recipe)}
        holder.tvTitle.text = recipe.title
        holder.tvFridge.text = recipe.getMatchesCountAsString()
        holder.tvKcal.text = "${recipe.kcal} ккал"
        holder.tvTime.text = recipe.time
        Picasso.get()
            .load(recipe.imageUrl)
            .into(holder.ivImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_dish_title)
        val tvFridge: TextView = itemView.findViewById(R.id.tv_dish_fridge)
        val tvKcal: TextView = itemView.findViewById(R.id.tv_dish_kkal)
        val tvTime: TextView = itemView.findViewById(R.id.tv_dish_cook_time)
        val ivImage: ImageView = itemView.findViewById(R.id.iv_dish_image)
    }
}