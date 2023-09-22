package com.bernardo.appdepizzaria

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bernardo.appdepizzaria.adapter.ProductAdapter
import com.bernardo.appdepizzaria.databinding.ActivityMainLayoutBinding
import com.bernardo.appdepizzaria.listItems.Products
import com.bernardo.appdepizzaria.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainLayoutBinding
    private lateinit var productAdapter: ProductAdapter
    private val products = Products()
    private val productList: MutableList<Product> = mutableListOf()
    var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        CoroutineScope(Dispatchers.IO).launch {
            products.getProducts().collectIndexed{ index, value ->
                for (p in value) {
                    productList.add(p)
                }
            }
        }

        val recyclerViewProducts = binding.recyclerViewProducts
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)
        recyclerViewProducts.setHasFixedSize(true)
        productAdapter = ProductAdapter(this, productList)
        recyclerViewProducts.adapter = productAdapter

        binding.btAll.setOnClickListener {
            clicked = true
            if (clicked) {
                binding.btAll.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btAll.setTextColor(Color.WHITE)
                binding.btChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btChicken.setTextColor(R.color.dark_grey)
                binding.btPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btPizza.setTextColor(R.color.dark_grey)
                binding.btKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btKebab.setTextColor(R.color.dark_grey)
                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "All"
            }
        }

        binding.btChicken.setOnClickListener {
            clicked = true
            if (clicked) {
                binding.btChicken.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btChicken.setTextColor(Color.WHITE)
                binding.btAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btAll.setTextColor(R.color.dark_grey)
                binding.btPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btPizza.setTextColor(R.color.dark_grey)
                binding.btKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btKebab.setTextColor(R.color.dark_grey)
                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "Chicken"
            }
        }

        binding.btPizza.setOnClickListener {
            clicked = true
            if (clicked) {
                binding.btPizza.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btPizza.setTextColor(Color.WHITE)
                binding.btAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btAll.setTextColor(R.color.dark_grey)
                binding.btChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btChicken.setTextColor(R.color.dark_grey)
                binding.btKebab.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btKebab.setTextColor(R.color.dark_grey)
                binding.recyclerViewProducts.visibility = View.VISIBLE
                binding.txtListTitle.text = "Pizza"
            }
        }

        binding.btKebab.setOnClickListener {
            clicked = true
            if (clicked) {
                binding.btKebab.setBackgroundResource(R.drawable.bg_button_enabled)
                binding.btKebab.setTextColor(Color.WHITE)
                binding.btAll.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btAll.setTextColor(R.color.dark_grey)
                binding.btChicken.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btChicken.setTextColor(R.color.dark_grey)
                binding.btPizza.setBackgroundResource(R.drawable.bg_button_disabled)
                binding.btPizza.setTextColor(R.color.dark_grey)
                binding.recyclerViewProducts.visibility = View.INVISIBLE
                binding.txtListTitle.text = "Kebab"
            }
        }
    }
}