package com.tristanroussel.miaou.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.model.BreedImage
import com.tristanroussel.miaou.utils.changeVisibility
import com.tristanroussel.miaou.view.adapter.BreedAdapter
import com.tristanroussel.miaou.viewModel.BreedViewModel
import com.tristanroussel.miaou.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_breed.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class BreedFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var breedNameTextView: TextView
    private lateinit var breedImage: ImageView

    var breed: Breed? = null
    private val breedViewModel by lazy { initViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_breed, container, false).apply {
                backButton = breed_back
                breedNameTextView = breed_name
                breedImage = breed_image
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewModel()
        configView()
        configAction()

        breed?.id?.let { breedViewModel?.getBreedImages(it) }
    }

    private fun initViewModel(): BreedViewModel? =
            ViewModelProviders.of(this).get(BreedViewModel::class.java)

    private fun bindViewModel() {
        breedViewModel?.let {
            it.images.observe(this, Observer { images ->
                if (images.isNotEmpty()) configImages(images)
            })
        }
    }

    private fun configView() {
        breed?.let {
            breedNameTextView.text = it.name
        }
    }

    private fun configAction() {
        backButton.setOnClickListener { activity?.onBackPressed() }
    }

    private fun configImages(images: List<BreedImage>) {
        images.first().url?.let {
            Glide.with(this)
                    .load(it)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(breedImage)
        }
    }

    companion object {

        fun instance(breed: Breed): BreedFragment = BreedFragment().apply { this.breed = breed }
    }
}