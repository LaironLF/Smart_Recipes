package com.laironlf.smartRecipes.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.Recipe
import com.squareup.picasso.Picasso

class RecipeListAdapter(private val onItemClick: (Recipe) -> Unit) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    var items : List<Recipe> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tv_dish_cook_time)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_dish_title)
        val tvKkal: TextView = itemView.findViewById(R.id.tv_dish_kkal)
        val tvFridgeCount: RadioButton = itemView.findViewById(R.id.rb_fridgeCount)
        val recipeImage: ImageView = itemView.findViewById(R.id.iv_dish_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = items[position]
        holder.tvTime.text = recipe.time
        holder.tvKkal.text = "${recipe.kcal} ккал"
        holder.tvTitle.text = recipe.title
        holder.tvFridgeCount.text = "${recipe.matchesProducts.size} / ${recipe.ingredients.size}"
        holder.tvFridgeCount.isChecked = recipe.matchesProducts.size == recipe.ingredients.size
//        holder.recipeImage.setImageResource(R.drawable.test_image)
        Picasso.get()
            .load("https://telegra.ph/file/d8093794d3c21315adeea.jpg")
            .into(holder.recipeImage)
        holder.itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }

    override fun getItemCount(): Int = items.size


}
