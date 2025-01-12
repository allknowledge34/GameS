package com.sachin.gamex.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sachin.gamex.Adapter.ListItemsAdapter
import com.sachin.gamex.R
import com.sachin.gamex.ViewModel.MainViewModel
import com.sachin.gamex.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {
    private lateinit var binding: ActivityListItemsBinding
    private val viewModel=MainViewModel()
    private var title:String=""
    private var id:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            binding.backBtn.setOnClickListener { finish() }
            progressBarList.visibility= View.VISIBLE
            viewModel.recommended.observe(this@ListItemsActivity, Observer {
                viewList.layoutManager=GridLayoutManager(this@ListItemsActivity, 2)
                viewList.adapter=ListItemsAdapter(it)
                progressBarList.visibility=View.GONE
            })
            viewModel.loadFiltered(id)
        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryTxt.text=title
    }
}