package com.tristanroussel.miaou.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.view.adapter.viewHolder.BreedViewHolder

class BreedAdapter(private val breeds: List<Breed>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return BreedViewHolder(inflater.inflate(R.layout.item_breed, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BreedViewHolder)?.configBreed(breeds[position])
    }

    override fun getItemCount(): Int = breeds.size
}