package com.example.testtaskfromvk.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.testtaskfromvk.R
import com.example.testtaskfromvk.databinding.ActivityMainBinding
import com.example.testtaskfromvk.ui.listproducts.ProductsFragment
import com.example.testtaskfromvk.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ProductsFragment())
            .commit()

        with(binding){
            bottomNavigationView.selectedItemId = R.id.productFragment
            bottomNavigationView.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.productFragment -> setCurrFragment(ProductsFragment())
                    R.id.searchFragment -> setCurrFragment(SearchFragment())
                }
                true
            }
        }

    }
    private fun setCurrFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,fragment)
            commit()
        }
    }
}