package com.tristanroussel.miaou.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.model.Breed
import com.tristanroussel.miaou.service.CatService
import com.tristanroussel.miaou.utils.asListOfType
import com.tristanroussel.miaou.view.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

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
