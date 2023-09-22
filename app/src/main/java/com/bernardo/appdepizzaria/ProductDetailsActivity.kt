package com.bernardo.appdepizzaria

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.bernardo.appdepizzaria.databinding.ActivityProductDetailsBinding
import java.text.DecimalFormat

class ProductDetailsActivity : Activity() {

    private lateinit var binding: ActivityProductDetailsBinding
    var amount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#E0E0E0")

        val imgProduct = intent.extras!!.getInt("imgProduct")
        val name = intent.extras!!.getString("name")
        val price = intent.extras!!.getString("price")!!.toDouble()
        var newPrice = price
        val decimalFormat = DecimalFormat.getCurrencyInstance()

        binding.imgProduct.setBackgroundResource(imgProduct)
        binding.txtProductName.text = "$name"
        binding.txtProductPrice.text = decimalFormat.format(price)

        binding.btBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btToIncrease.setOnClickListener {
            amount++
            binding.txtAmount.text = "$amount"
            newPrice = amount * price
            binding.txtProductPrice.text = decimalFormat.format(newPrice)
        }

        binding.btToDecrease.setOnClickListener {
            if (amount > 1) {
                amount--
                binding.txtAmount.text = "$amount"
                newPrice = amount * price
                binding.txtProductPrice.text = decimalFormat.format(newPrice)
            }
        }

        binding.btConfirm.setOnClickListener {
            val mustard = binding.btMustard
            val ketchup = binding.btKetchup
            val lemonSoda = binding.btLemonSoda
            val juice = binding.btJuice

            val saucesAndDrinks = when {
                mustard.isChecked -> {
                    "Mustard"
                }
                ketchup.isChecked -> {
                    "Ketchup"
                }
                lemonSoda.isChecked -> {
                    "Lemon Soda"
                }
                juice.isChecked -> {
                    "Juice"
                }
                else -> {
                    ""
                }
            }

            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("amount", amount)
            intent.putExtra("total", newPrice)
            intent.putExtra("saucesAndDrinks", saucesAndDrinks)
            startActivity(intent)
        }
    }
}