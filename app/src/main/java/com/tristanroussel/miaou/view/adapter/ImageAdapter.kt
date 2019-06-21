package com.tristanroussel.miaou.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tristanroussel.miaou.R
import kotlinx.android.synthetic.main.item_image.view.*

class ImageAdapter(private val context: Context, private val imagesUrl: List<String>)
    : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_image, container, false)
        val imageView = view.breed_image

        Glide.with(context)
                .load(imagesUrl[position])
                .apply(RequestOptions().placeholder(R.drawable.placeholder).centerCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        (obj as? View)?.let { container.removeView(it) }
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = imagesUrl.size
}