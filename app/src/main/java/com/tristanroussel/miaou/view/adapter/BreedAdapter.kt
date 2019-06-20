package com.tristanroussel.miaou.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.view.adapter.viewHolder.BreedViewHolder
import com.tristanroussel.miaou.view.adapter.viewHolder.HeaderViewHolder

class BreedAdapter(private val data: List<Any>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewType {
        HEADER_VIEW_TYPE,
        BREED_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return when (viewType) {
            ViewType.BREED_VIEW_TYPE.ordinal -> {
                BreedViewHolder(inflater.inflate(R.layout.item_breed, parent, false))
            }
            else -> {
                HeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val datum = data[position]
        when (getItemViewType(position)) {
            ViewType.HEADER_VIEW_TYPE.ordinal -> {
            }
            ViewType.BREED_VIEW_TYPE.ordinal -> {
                if (datum is Breed && holder is BreedViewHolder) {
                    holder.configBreed(datum)
                }
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int =
            when (data[position]) {
                is Breed -> ViewType.BREED_VIEW_TYPE.ordinal
                else -> ViewType.HEADER_VIEW_TYPE.ordinal
            }
}