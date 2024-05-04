package com.tech.nasaapodapp.nasa_feature.screen

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_DETAILS__SCREEN
import com.tech.nasaapodapp.nasa_feature.presentation.screen.NasaApodItemScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NasaApodItemScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateIfApodDetailScreenIsDisplayed() {
        composeTestRule.setContent {
            NasaApodItemScreen(
                model = "model",
                explanation = "explanation",
            )
        }
        composeTestRule.onNodeWithText(APOD_DETAILS__SCREEN).isDisplayed()
    }
}