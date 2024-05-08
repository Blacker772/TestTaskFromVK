package com.example.testtaskfromvk.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.testtaskfromvk.R
import com.example.testtaskfromvk.databinding.FragmentSearchBinding
import com.example.testtaskfromvk.ui.listproducts.ProductsFragment
import com.example.testtaskfromvk.ui.listproducts.detailsproduct.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel by viewModels<SearchViewModel>()
    private val adapter = SearchAdapter()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSearchList.adapter = adapter
        adapter.onItemClickListener {
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


        binding.svSearch.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lifecycleScope.launch {
                    newText?.let { viewModel.searchProduct(it) }
                    viewModel.liveData.observe(viewLifecycleOwner){
                        adapter.submitList(it.products)
                    }
                }
                return true
            }

        })
    }
}