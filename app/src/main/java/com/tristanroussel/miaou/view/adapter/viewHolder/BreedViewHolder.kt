package com.tristanroussel.miaou.view.adapter.viewHolder

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tristanroussel.miaou.listener.OnBreedClickListener
import com.tristanroussel.miaou.model.Breed
import kotlinx.android.synthetic.main.item_breed.view.*

class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val breedLayout: CardView = itemView.breed_layout
    private val breedNameTextView: TextView = itemView.breed_name

    fun configBreed(breed: Breed, onBreedClickListener: OnBreedClickListener?) {
        breedNameTextView.text = breed.name ?: ""
        breedLayout.setOnClickListener { onBreedClickListener?.onBreedClick(breed) }
    }
}