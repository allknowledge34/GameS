package com.sachin.gamex.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.project1762.Helper.ManagmentCart
import com.sachin.gamex.Adapter.PicAdapter
import com.sachin.gamex.Adapter.SelectModelAdapter
import com.sachin.gamex.Model.ItemsModel
import com.sachin.gamex.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        getBundle()
        initList()

    }

    private fun initList() {
        val modeList=ArrayList<String>()
        for (models in item.model){
            modeList.add(models)
        }

        binding.modelList.adapter=SelectModelAdapter(modeList)
        binding.modelList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)

        val picList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            picList.add(imageUrl)
        }

        Glide.with(this)
            .load(picList[0])
            .into(binding.img)

        binding.picList.adapter=PicAdapter(picList){selectedImageUrl ->
            Glide.with(this)
                .load(selectedImageUrl)
                .into(binding.img)
        }

        binding.picList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getBundle() {
        item=intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price
        binding.ratingTxt.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener {
            item.numberInCart=numberOrder
            managmentCart.insertItem(item)

        }
        binding.backBtn.setOnClickListener { finish()}
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
    }
}