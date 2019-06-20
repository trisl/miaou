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

    override fun onStart() {
        super.onStart()

        bindViewModel()
        configBreeds(ArrayList())
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

    private fun displayLoader(loading: Boolean) {
        loader.visibility = loading.changeVisibility()
    }

    private fun configBreeds(breeds: List<Breed>) {
        val data = ArrayList<Any>().apply {
            add("header")
            addAll(breeds)
        }
        breedsCollection.adapter = BreedAdapter(data)
        breedsCollection.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}