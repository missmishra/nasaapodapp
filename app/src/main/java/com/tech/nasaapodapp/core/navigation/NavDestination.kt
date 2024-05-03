package com.tech.nasaapodapp.core.navigation

sealed class NavDestination(val route: String) {
    companion object{
        const val APODLIST= "apod_listing"
        const val APODDETAIL = "apod_detail"
        const val LIST_TITLE = "Astronomy Pictures"
        const val DETAIL_TITLE = "APOD DETAIL"
    }
    class ApodListing: NavDestination(APODLIST)
    class ApodDetail: NavDestination(APODDETAIL)
}