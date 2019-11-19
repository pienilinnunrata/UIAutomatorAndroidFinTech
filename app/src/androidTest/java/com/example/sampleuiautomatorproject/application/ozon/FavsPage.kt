package com.example.sampleuiautomatorproject.application.ozon

import com.example.sampleuiautomatorproject.util.byStringRes
import com.example.sampleuiautomatorproject.util.byText
import com.example.sampleuiautomatorproject.util.ext.waitFindObject

class FavsPage {

    private val toolbarSelector = byStringRes("ru.ozon.app.android:id/toolbarTb")
    private val titleSelector = byStringRes("ru.ozon.app.android:id/titleTv") // if one shown

    inline fun assert(block: FavsPage.Assert.() -> Unit) = Assert().block()

    inner class Assert {
        fun checkFavsOpened(opened: Boolean = true) {
            org.junit.Assert.assertEquals(
                "favs not opened",
                opened,
                toolbarSelector.hasChild(byText("Избранное")).waitFindObject().isEnabled
            )
        }

        fun checkFavedBookShown(title: String) {
            org.junit.Assert.assertEquals(
                "no book with name $title",
                title,
                titleSelector.waitFindObject().text
            )
        }
    }

    companion object {
        inline operator fun <T> invoke(block: FavsPage.() -> T) = FavsPage().block()
    }
}