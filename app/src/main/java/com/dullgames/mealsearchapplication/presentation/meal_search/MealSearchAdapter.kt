package com.dullgames.mealsearchapplication.presentation.meal_search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dullgames.mealsearchapplication.databinding.MealItemViewBinding
import com.dullgames.mealsearchapplication.domain.model.Meal

class MealSearchAdapter : RecyclerView.Adapter<MealSearchAdapter.ViewHolder>() {

    private var listener: ((Meal) -> Unit)? = null
    var list = mutableListOf<Meal>()

    fun setContent(list: MutableList<Meal>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealSearchAdapter.ViewHolder {
        val binding =
            MealItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealSearchAdapter.ViewHolder, position: Int) {
        holder.viewHolder.meal = list[position]
        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun itemClickListener(l:(Meal)->Unit){
        listener = l
    }

    class ViewHolder(val viewHolder: MealItemViewBinding) : RecyclerView.ViewHolder(viewHolder.root)
}