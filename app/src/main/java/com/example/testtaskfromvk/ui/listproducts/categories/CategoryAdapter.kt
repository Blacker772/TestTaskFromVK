package com.example.testtaskfromvk.ui.listproducts.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskfromvk.databinding.CategoryItemBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val list = mutableListOf<String>()
    fun addCategory(category: List<String>){
        list.clear()
        list.addAll(category)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: String){
            binding.tvCategory.text = category
        }
    }
}