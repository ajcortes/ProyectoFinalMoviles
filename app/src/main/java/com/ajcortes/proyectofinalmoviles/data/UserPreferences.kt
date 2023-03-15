package com.ajcortes.proyectofinalmoviles.data

data class UserPreferences (
    val username : String = "",
    val viewPagerVisto : Boolean = false,
    val navagcion : Boolean = false,
    val photo : String = ""
){
    companion object{
        const val SETTINGS_FILE = "settings"
        const val USER_NAME = "username"
        const val VIEW_PAGER_VISTO = "viewPagerVisto"
        const val PHOTO = "photo"
    }
}