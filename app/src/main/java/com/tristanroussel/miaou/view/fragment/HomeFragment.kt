package com.tristanroussel.miaou.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.utils.changeVisibility
import com.tristanroussel.miaou.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var loader: ProgressBar
    private lateinit var responseTextView: TextView

    private val homeViewModel by lazy { initViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_home, container, false).apply {
                loader = breeds_loader
                responseTextView = response_json
            }

    override fun onStart() {
        super.onStart()

        bindViewModel()
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
        responseTextView.visibility = loading.changeVisibility(invert = true)
    }

    private fun configBreeds(breeds: List<Breed>) {
        responseTextView.text = breeds.map { it.name }.toString()
    }
}