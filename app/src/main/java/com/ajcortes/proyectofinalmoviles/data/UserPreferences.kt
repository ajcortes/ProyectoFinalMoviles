package com.ajcortes.proyectofinalmoviles.data

data class UserPreferences (
    val username : String = "",
    val viewPagerVisto : Boolean = false
){
    companion object{
        const val SETTINGS_FILE = "settings"
        const val USER_NAME = "username"
        const val VIEW_PAGER_VISTO = false
    }
}