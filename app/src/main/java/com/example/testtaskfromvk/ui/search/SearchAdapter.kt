package com.example.testtaskfromvk.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.testtaskfromvk.data.productmodel.Product
import com.example.testtaskfromvk.databinding.SearchItemBinding

class SearchAdapter:ListAdapter<Product,SearchAdapter.SearchViewHolder>(DIFF_UTIL) {

    private var onItemClick:((product: Product) -> Unit)? = null
    fun onItemClickListener(onItemClick: (product: Product) -> Unit){
        this.onItemClick = onItemClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SearchItemBinding.inflate(inflater, parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(currentList[position], onItemClick)
    }

    inner class SearchViewHolder(private val binding: SearchItemBinding): ViewHolder(binding.root){
        fun bind(data: Product, onItemClick:((product: Product) -> Unit)?){
            with(binding){
                ivImage.load(data.image)
                tvTitle.text = data.title
                tvDesc.text = data.description
                tvRating.text = data.rating
                tvPrice.text = "${data.price} $"
                cvItem.setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: Product,
                newItem: Product
            ): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.title == newItem.title &&
                        oldItem.description == newItem.description
            }
        }
    }
}