package com.example.sampleuiautomatorproject.test

import com.example.sampleuiautomatorproject.page.Ozon
import org.junit.Test

class OzonTest {

    @Test
    fun itemHint() {
        Ozon {
            open()
            tapSearch()
            fillSearchWithText("философия java")
            assert {
                checkName("Философия Java")
                checkPrice("1 499 \u20BD")
            }
        }
    }

    @Test
    fun addToFavs() {
        Ozon {
            open()
            tapSearch()
            fillSearchWithText("философия java")
            tapItemHint()
            tapFavoriteBtn()
            tapFavoritesMenu()
            assert {
                checkFavsOpened()
                checkFavedBookShown("Философия Java")
            }
        }
    }

    @Test
    fun emailValidation() {
        val errorText = "Некорректный формат почты"
        Ozon {
            open()
            tapMenuProfile()
            assert {
                checkRegisterFormOpened()
            }
            tapLoginWithEmail()
            fillEmailField("123")
            tapSubmitBtn()
            assert {
                checkEmailError(errorText)
            }

            fillEmailField("123@ya.ru")
            tapSubmitBtn()
            assert {
                checkEmailError(errorText, false)
            }
        }
    }

    @Test
    fun registerOfferWhenWritingComment() {
        Ozon {
            open()
            tapSearch()
            fillSearchWithText("философия java")
            tapItemHint()
            tapWriteComment()
            assert {
                checkRegisterFormOpened()
            }
        }
    }
}