package com.tristanroussel.miaou.view.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tristanroussel.miaou.model.Breed
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val breedNameTextView: TextView = itemView.breed_name

    fun configBreed(breed: Breed) {
        breedNameTextView.text = breed.name ?: ""
    }
}