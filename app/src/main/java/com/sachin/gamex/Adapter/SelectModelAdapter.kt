package com.sachin.gamex.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sachin.gamex.R
import com.sachin.gamex.databinding.ViewholderModelBinding


class SelectModelAdapter(val items:MutableList<String>) :RecyclerView.Adapter<SelectModelAdapter.Viewholder>() {

    private var selectedPosition =-1
    private var lastSelectedPosition=-1
    private lateinit var context: Context
    inner class Viewholder (val binding: ViewholderModelBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectModelAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderModelBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SelectModelAdapter.Viewholder, position: Int) {
        holder.binding.modelTxt.text=items[position]

        holder.binding.root.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition==position){
            holder.binding.modelLayout.setBackgroundResource(R.drawable.purple_bg_selected)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.purple))
        }else{
            holder.binding.modelLayout.setBackgroundResource(R.drawable.gray_bg)
            holder.binding.modelTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }

    override fun getItemCount(): Int =items.size
}