package com.example.sampleuiautomatorproject.page

import androidx.test.uiautomator.UiScrollable
import com.example.sampleuiautomatorproject.application.AbstractApplication
import com.example.sampleuiautomatorproject.util.*
import com.example.sampleuiautomatorproject.util.ext.waitFindObject
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import androidx.test.uiautomator.UiSelector

class Ozon: AbstractApplication("ru.ozon.app.android") {

    private val searchSelector = byStringRes("ru.ozon.app.android:id/searchTv")
    private val searchOpenedSelector = byStringRes("ru.ozon.app.android:id/search_src_text")
    private val itemNameSelector = byText("Философия Java")
    private val itemPriceSelector = byStringRes("ru.ozon.app.android:id/priceTv")
    private val itemHintSelector = byContentDesc("Философия Java")
    private val favoriteBtnSelector = byStringRes("ru.ozon.app.android:id/favoritesIv")
    private val favoriteMenuSelector = byStringRes("ru.ozon.app.android:id/menu_favorites")
    private val toolbarSelector = byStringRes("ru.ozon.app.android:id/toolbarTb")
    private val titleSelector = byStringRes("ru.ozon.app.android:id/titleTv") // if one shown
    private val profileMenuSelector = byStringRes("ru.ozon.app.android:id/menu_profile")
    private val loginWithEmailSelector = byText("Войти по почте")
    private val emailFieldSelector = byStringRes("ru.ozon.app.android:id/emailEt")
    private val submitBtnSelector = byStringRes("ru.ozon.app.android:id/submitBtn")
    private val errorTextSelector = byStringRes("ru.ozon.app.android:id/textinput_error")
    private val writeCommentBtnSelector = byStringRes("ru.ozon.app.android:id/writeReviewBtn")

    fun tapSearch() {
        searchSelector.waitFindObject().click()
    }

    fun fillSearchWithText(text: String) {
        searchOpenedSelector.waitFindObject().text = text
    }

    fun tapItemHint() {
        itemHintSelector.waitFindObject().click()
    }

    fun tapFavoriteBtn() {
        favoriteBtnSelector.waitFindObject().click()
    }

    fun tapFavoritesMenu() {
        favoriteMenuSelector.waitFindObject().click()
    }

    fun tapMenuProfile() {
        profileMenuSelector.waitFindObject().click()
    }

    fun tapLoginWithEmail() {
        loginWithEmailSelector.waitFindObject().click()
    }

    fun fillEmailField(text: String) {
        emailFieldSelector.waitFindObject().text = text
    }

    fun tapSubmitBtn() {
        submitBtnSelector.waitFindObject().click()
    }

    fun tapWriteComment() {
        val listView = UiScrollable(UiSelector().scrollable(true).resourceId("ru.ozon.app.android:id/listRv"))
        listView.scrollTextIntoView("Написать отзыв")
        writeCommentBtnSelector.waitFindObject().click()
    }

    inline fun assert(block: Assert.() -> Unit) = Assert().block()

    inner class Assert {
        fun checkName(name: String) {
            assertEquals(
                "wrong name",
                name,
                itemNameSelector.waitFindObject().text
            )
        }

        fun checkPrice(price: String) {
            assertEquals(
                "wrong sum",
                price,
                itemPriceSelector.waitFindObject().text
            )
        }

        fun checkFavsOpened(opened: Boolean = true) {
            assertEquals(
                "favs not opened",
                opened,
                toolbarSelector.hasChild(byText("Избранное")).waitFindObject().isEnabled
            )
        }

        fun checkFavedBookShown(title: String) {
            assertEquals(
                "no book with name $title",
                title,
                titleSelector.waitFindObject().text
            )
        }

        fun checkRegisterFormOpened(opened: Boolean = true) {
            assertEquals(
                "register form not opened",
                opened,
                titleSelector.waitFindObject().isEnabled
            )
        }

        fun checkEmailError(error: String, shown: Boolean = true) {
            if (shown)
                assertEquals(
                    "wrong error",
                    error,
                    errorTextSelector.waitFindObject().text
                )
            else
                assertNotEquals(
                    "email error shown",
                    error,
                    errorTextSelector.waitFindObject().text
                )
        }
    }

    companion object {
        inline operator fun <T> invoke(block: Ozon.() -> T) = Ozon().block()
    }
}