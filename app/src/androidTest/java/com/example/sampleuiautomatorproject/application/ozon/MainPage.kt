package com.example.sampleuiautomatorproject.application.ozon

import com.example.sampleuiautomatorproject.application.AbstractApplication
import com.example.sampleuiautomatorproject.util.*
import com.example.sampleuiautomatorproject.util.ext.waitFindObject

class MainPage: AbstractApplication("ru.ozon.app.android") {

    private val searchSelector = byStringRes("ru.ozon.app.android:id/searchTv")
    private val profileMenuSelector = byStringRes("ru.ozon.app.android:id/menu_profile")

    fun tapSearch(block: SearchPage.() -> Unit) {
        searchSelector.waitFindObject().click()
        SearchPage { block() }
    }

    fun tapMenuProfile(block: LoginPage.() -> Unit) {
        profileMenuSelector.waitFindObject().click()
        LoginPage { block() }
    }

    companion object {
        inline operator fun <T> invoke(block: MainPage.() -> T) = MainPage().block()
    }
}