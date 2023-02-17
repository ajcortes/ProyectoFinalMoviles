package com.ajcortes.proyectofinalmoviles.dependencies

import android.app.Application

class ProyectoFinalMoviles : Application() {

    private lateinit var _appContainer : AppContainer
    val appContainer get() = _appContainer

    override fun onCreate(){
        super.onCreate()
        _appContainer = AppContainer(this)
    }
}