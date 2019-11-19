package com.example.sampleuiautomatorproject.application.ozon

import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import com.example.sampleuiautomatorproject.util.byContentDesc
import com.example.sampleuiautomatorproject.util.byStringRes
import com.example.sampleuiautomatorproject.util.ext.waitFindObject
import org.junit.Assert

class ItemPage {

    private val favoriteBtnSelector = byStringRes("ru.ozon.app.android:id/favoritesIv")
    private val favoriteMenuSelector = byStringRes("ru.ozon.app.android:id/menu_favorites")
    private val deleteFromFavsBtnSelector = byContentDesc("Удалить из избранного")
    private val itemShownSelector = byStringRes("ru.ozon.app.android:id/imageIv")
    private val writeCommentBtnSelector = byStringRes("ru.ozon.app.android:id/writeReviewBtn")

    fun tapFavoriteBtn() {
        Assert.assertTrue(itemShownSelector.waitFindObject().isEnabled)
        favoriteBtnSelector.waitFindObject().click()
        Assert.assertTrue(deleteFromFavsBtnSelector.waitFindObject().isEnabled)
    }

    fun tapFavoritesMenu(block: FavsPage.() -> Unit) {
        favoriteMenuSelector.waitFindObject().click()
        FavsPage { block() }
    }

    fun tapWriteComment(block: LoginPage.() -> Unit) {
        val listView = UiScrollable(UiSelector().scrollable(true).resourceId("ru.ozon.app.android:id/listRv"))
        listView.scrollTextIntoView("Написать отзыв")
        writeCommentBtnSelector.waitFindObject().click()
        LoginPage { block() }
    }

    companion object {
        inline operator fun <T> invoke(block: ItemPage.() -> T) = ItemPage().block()
    }
}