package com.example.testtaskfromvk.ui.listproducts.detailsproduct

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.testtaskfromvk.R
import com.example.testtaskfromvk.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val image = arguments?.getString("image")
        val price = arguments?.getString("price")
        val stock = arguments?.getString("stock")
        val rating = arguments?.getString("rating")

        with(binding){
            tvTitle.text = title
            tvDesc.text = description
            tvPrice.text = "Цена: $price $"
            tvStock.text = "В наличии: $stock"
            tvRating.text = rating
            sivImage.load(image)
        }
    }
}