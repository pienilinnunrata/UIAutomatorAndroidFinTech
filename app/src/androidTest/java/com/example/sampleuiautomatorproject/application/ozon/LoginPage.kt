package com.example.sampleuiautomatorproject.application.ozon

import com.example.sampleuiautomatorproject.util.byStringRes
import com.example.sampleuiautomatorproject.util.byText
import com.example.sampleuiautomatorproject.util.ext.waitFindObject

class LoginPage {


    private val loginWithEmailSelector = byText("Войти по почте")
    private val titleSelector = byStringRes("ru.ozon.app.android:id/titleTv") // if one shown

    fun tapLoginWithEmail(block: RegisterPage.() -> Unit) {
        loginWithEmailSelector.waitFindObject().click()
        RegisterPage { block() }
    }

    inline fun assert(block: LoginPage.Assert.() -> Unit) = Assert().block()

    inner class Assert {
        fun checkRegisterFormOpened(opened: Boolean = true) {
            org.junit.Assert.assertEquals(
                "register form not opened",
                opened,
                titleSelector.waitFindObject().isEnabled
            )
        }
    }

    companion object {
        inline operator fun <T> invoke(block: LoginPage.() -> T) = LoginPage().block()
    }
}