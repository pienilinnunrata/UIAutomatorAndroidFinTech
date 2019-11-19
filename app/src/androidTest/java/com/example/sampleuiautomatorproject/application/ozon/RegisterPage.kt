package com.example.sampleuiautomatorproject.application.ozon

import com.example.sampleuiautomatorproject.util.byStringRes
import com.example.sampleuiautomatorproject.util.ext.waitFindObject

class RegisterPage {

    private val emailFieldSelector = byStringRes("ru.ozon.app.android:id/emailEt")
    private val submitBtnSelector = byStringRes("ru.ozon.app.android:id/submitBtn")
    private val errorTextSelector = byStringRes("ru.ozon.app.android:id/textinput_error")


    fun fillEmailField(text: String) {
        emailFieldSelector.waitFindObject().text = text
    }

    fun tapSubmitBtn() {
        submitBtnSelector.waitFindObject().click()
    }

    inline fun assert(block: Assert.() -> Unit) = Assert().block()

    inner class Assert {

        fun checkEmailError(error: String, shown: Boolean = true) {
            if (shown)
                org.junit.Assert.assertEquals(
                    "wrong error",
                    error,
                    errorTextSelector.waitFindObject().text
                )
            else
                org.junit.Assert.assertNotEquals(
                    "email error shown",
                    error,
                    errorTextSelector.waitFindObject().text
                )
        }
    }

    companion object {
        inline operator fun <T> invoke(block: RegisterPage.() -> T) = RegisterPage().block()
    }
}