package com.tristanroussel.miaou.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.listener.OnBreedClickListener
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.view.fragment.BreedFragment
import com.tristanroussel.miaou.view.fragment.HomeFragment

class HomeActivity : AppCompatActivity(), OnBreedClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showHomeFragment()
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, HomeFragment())
                .commit()
    }

    //region ON BREED CLICK LISTENER
    override fun onBreedClick(breed: Breed) {
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_layout, BreedFragment.instance(breed))
                .addToBackStack(breed.name)
                .commit()
    }
    //endregion
}
