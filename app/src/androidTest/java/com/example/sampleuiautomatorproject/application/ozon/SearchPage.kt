package com.example.sampleuiautomatorproject.application.ozon

import com.example.sampleuiautomatorproject.util.byContentDesc
import com.example.sampleuiautomatorproject.util.byStringRes
import com.example.sampleuiautomatorproject.util.byText
import com.example.sampleuiautomatorproject.util.ext.waitFindObject

class SearchPage {

    private val searchOpenedSelector = byStringRes("ru.ozon.app.android:id/search_src_text")
    private val itemHintSelector = byContentDesc("Философия Java")
    private val itemNameSelector = byText("Философия Java")
    private val itemPriceSelector = byStringRes("ru.ozon.app.android:id/priceTv")

    fun fillSearchWithText(text: String) {
        searchOpenedSelector.waitFindObject().text = text
    }

    fun tapItemHint(block: ItemPage.() -> Unit) {
        itemHintSelector.waitFindObject().click()
        ItemPage { block() }
    }

    inline fun assert(block: SearchPage.Assert.() -> Unit) = Assert().block()

    inner class Assert {
        fun checkName(name: String) {
            org.junit.Assert.assertEquals(
                "wrong name",
                name,
                itemNameSelector.waitFindObject().text
            )
        }

        fun checkPrice(price: String) {
            org.junit.Assert.assertEquals(
                "wrong price",
                price,
                itemPriceSelector.waitFindObject().text
            )
        }
    }

    companion object {
        inline operator fun <T> invoke(block: SearchPage.() -> T) = SearchPage().block()
    }

}