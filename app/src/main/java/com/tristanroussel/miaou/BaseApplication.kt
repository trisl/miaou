package com.tristanroussel.miaou

import android.app.Application
import com.tristanroussel.miaou.service.Service

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initService()
    }

    private fun initService() {
        Service.configRetrofit(getString(R.string.cat_api_url), getString(R.string.cat_api_key))
    }
}