package com.example.testtaskfromvk.ui.listproducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.testtaskfromvk.R
import com.example.testtaskfromvk.data.productmodel.Product
import com.example.testtaskfromvk.databinding.FragmentProductsBinding
import com.example.testtaskfromvk.ui.listproducts.categories.CategoryViewModel
import com.example.testtaskfromvk.ui.listproducts.detailsproduct.DetailsFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModels<ProductsViewModel>()
    private val productAdapter = ProductsAdapter()
    private val viewModelCategory by viewModels<CategoryViewModel>()
    private var pageInx: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModelCategory.getCategories()
        }
        viewModelCategory.liveData.observe(viewLifecycleOwner) {
            binding.tabs.addTab(binding.tabs.newTab().setText("all"))
            for (i in it){
                binding.tabs.addTab(binding.tabs.newTab().setText(i))
            }
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(p0: TabLayout.Tab?) {
                pageInx = 0
                productAdapter.submitList(emptyList())
               if (p0?.position == 0) {
                   lifecycleScope.launch {
                       viewModel.getProduct(pageInx)
                   }
               }else{
                   lifecycleScope.launch {
                       viewModel.getProductByCategory(p0?.text.toString(),pageInx)
                   }
               }
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabReselected(p0: TabLayout.Tab?) {}
        })

        //Лист с продуктами
        binding.rvList.adapter = productAdapter

        productAdapter.onItemClickListener {
            val bundle = bundleOf(
                "title" to it.title,
                "description" to it.description,
                "price" to it.price,
                "rating" to it.rating,
                "stock" to it.stock,
                "image" to it.image,
            )
            parentFragmentManager.beginTransaction()
                .addToBackStack(ProductsFragment::class.java.canonicalName)
                .replace(R.id.fragmentContainer, DetailsFragment::class.java, bundle)
                .commit()
        }
        binding.rvList.addOnScrollListener(object : EndlessScrollListener(){
            override fun onLoadMore(page: Int) {
                pageInx++
                lifecycleScope.launch {
                    viewModel.getProduct(pageInx)
                }
            }
        })

        viewModel.liveData.observe(viewLifecycleOwner) { data ->
            data?.let {
                val listPage = mutableListOf<Product>()
                listPage.addAll(productAdapter.currentList)
                listPage.addAll(it.products)
                productAdapter.submitList(listPage)
            }
        }
        lifecycleScope.launch {
            viewModel.getProduct(pageInx)
        }
    }
}