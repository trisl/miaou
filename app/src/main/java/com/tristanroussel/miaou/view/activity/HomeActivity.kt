package com.tristanroussel.miaou.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.view.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHomeFragment()
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, HomeFragment())
                .commit()
    }
}
