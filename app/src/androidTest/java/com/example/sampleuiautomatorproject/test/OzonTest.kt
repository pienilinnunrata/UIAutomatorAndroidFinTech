package com.example.sampleuiautomatorproject.test

import com.example.sampleuiautomatorproject.application.ozon.MainPage
import org.junit.Test

class OzonTest: AbstractApplicationTest<MainPage>(
    MainPage()
) {

    @Test
    fun itemHint() {
        MainPage {
            open()
            tapSearch {
                fillSearchWithText("философия java")
                assert {
                    checkName("Философия Java")
                    checkPrice("1 499 \u20BD")
                }
            }
        }
    }

    @Test
    fun addToFavs() {
        MainPage {
            open()
            tapSearch {
                fillSearchWithText("философия java")
                tapItemHint {
                    tapFavoriteBtn()
                    tapFavoritesMenu {
                        assert {
                            checkFavsOpened()
                            checkFavedBookShown("Философия Java")
                        }
                    }
                }
            }
        }
    }

    @Test
    fun emailValidation() {
        val errorText = "Некорректный формат почты"
        MainPage {
            open()
            tapMenuProfile {
                assert {
                    checkRegisterFormOpened()
                }
                tapLoginWithEmail {
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
        }
    }

    @Test
    fun registerOfferWhenWritingComment() {
        MainPage {
            open()
            tapSearch {
                fillSearchWithText("философия java")
                tapItemHint {
                    tapWriteComment {
                        assert {
                            checkRegisterFormOpened()
                        }
                    }
                }
            }
        }
    }
}