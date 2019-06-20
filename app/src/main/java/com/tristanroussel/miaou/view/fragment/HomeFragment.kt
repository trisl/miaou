package com.tristanroussel.miaou.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.listener.OnBreedClickListener
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.utils.changeVisibility
import com.tristanroussel.miaou.view.adapter.BreedAdapter
import com.tristanroussel.miaou.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var loader: ProgressBar
    private lateinit var breedsCollection: RecyclerView

    private val homeViewModel by lazy { initViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false).apply {
                loader = breeds_loader
                breedsCollection = breeds_collection
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindViewModel()
        initBreedsCollection()
        homeViewModel?.getBreeds()
    }

    private fun initViewModel(): HomeViewModel? =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

    private fun bindViewModel() {
        homeViewModel?.let {
            it.isLoading.observe(this, Observer<Boolean> { loading ->
                displayLoader(loading ?: false)
            })
            it.breeds.observe(this, Observer { breeds ->
                if (breeds.isNotEmpty()) configBreeds(breeds)
            })
        }
    }

    private fun initBreedsCollection() {
        val onBreedClickListener = activity as? OnBreedClickListener
        breedsCollection.adapter = BreedAdapter(ArrayList<Any>().apply { add("header") }, onBreedClickListener)
        breedsCollection.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        breedsCollection.addOnScrollListener(InfiniteScrollListener())
    }

    private fun displayLoader(loading: Boolean) {
        loader.visibility = loading.changeVisibility()
    }

    @Suppress("UNCHECKED_CAST")
    private fun configBreeds(breeds: List<Breed>) {
        (breedsCollection.adapter as? BreedAdapter)?.addData(breeds as ArrayList<Any>)
    }

    private inner class InfiniteScrollListener : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            val breedChild = recyclerView.getChildAt(recyclerView.childCount - 1)
            val isChildVisible = recyclerView.layoutManager
                    ?.isViewPartiallyVisible(breedChild, true, false)
                    ?: false
            if (isChildVisible) {
                homeViewModel?.getBreeds()
            }
        }
    }
}