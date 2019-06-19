package com.tristanroussel.miaou.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.tristanroussel.miaou.R
import com.tristanroussel.miaou.service.CatService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var responseTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        responseTextView = json_response
    }

    override fun onStart() {
        super.onStart()

        CatService.getBreeds { body, success ->
            if (success) responseTextView.text = body?.toString()
        }
    }
}
