package com.laironlf.smartRecipes.presentation.adapters.lllists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.post.IngredientPost
import com.laironlf.smartRecipes.presentation.adapters.lllists.base.BaseLinearLayoutList

class IngredientsPostAdapter(
    parent: ViewGroup,
    val onDeleteAction: (IngredientPost, Int) -> Unit
): BaseLinearLayoutList<IngredientsPostAdapter.ViewHolder>(parent) {

    var ingredients: List<IngredientPost> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount(): Int = ingredients.size

    fun deleteItem(position: Int){
        val list = ArrayList(ingredients)
        list.removeAt(position)
        ingredients = list
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient_post, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredients[position]
//        Toast.makeText(holder.itemView.context, "${ingredient.quantity} ${ingredient.measureType.id} ${ingredient.product.title}", Toast.LENGTH_SHORT).show()
        holder.tvTitle.text = ingredient.product.title

        holder.tvCount.text = if (ingredient.quantity > 0)
                "${ingredient.quantity} ${ingredient.measureType.title}" else ingredient.measureType.title

        holder.ivCheckIcon.setOnClickListener {
            onDeleteAction(ingredient, position)
        }
    }

    class ViewHolder(itemView: View) : BaseLinearLayoutList.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_ingredientTitle)
        val tvCount: TextView = itemView.findViewById(R.id.tv_ingredientCount)
        val ivCheckIcon: ImageView = itemView.findViewById(R.id.iv_checked)
    }
}