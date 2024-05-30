package com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.domain.models.Product

class ProductListAdapter(val onProductClick:(Product, Int) -> Unit ): RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    
    var productList : List<Product> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun deleteItem(position: Int){
        val list : ArrayList<Product> = ArrayList(productList)
        list.removeAt(position)
        productList = list
    }

    override fun getItemCount(): Int = productList.size
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.tvTitle.text = product.title
        holder.itemView.setOnClickListener{
            onProductClick(product, position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle : TextView = itemView.findViewById(R.id.tv_productTitle)
//        val ibDelete : ImageButton = itemView.findViewById(R.id.ib_deleteProduct)
    }
}