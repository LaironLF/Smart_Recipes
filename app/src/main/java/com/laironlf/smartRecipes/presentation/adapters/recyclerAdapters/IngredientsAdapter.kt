package com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.Ingredient
import com.laironlf.smartRecipes.domain.models.Product

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    var ingredients: List<Ingredient> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var matches: List<Product> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = ingredients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_product, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
        holder.tvTitle.text = ingredient.product.title
        holder.tvCount.text = "${ingredient.count} ${ingredient.measureType}"
        if (matches.any { product -> ingredient.product.id == product.id }){
            holder.setChecked()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_ingredientTitle)
        val tvCount: TextView = itemView.findViewById(R.id.tv_ingredientCount)
        val ivCheckIcon: ImageView = itemView.findViewById(R.id.iv_checked)
        val cvBack : CardView = itemView.findViewById(R.id.cv_ingredientBack)
        @SuppressLint("ResourceType")
        fun setChecked(){
            ivCheckIcon.setImageResource(R.drawable.ic_checked)
        }
    }


}
