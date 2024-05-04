package com.tech.nasaapodapp.nasa_feature.compose

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_CARD_ITEM
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.presentation.composable.CardsItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CardItemKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val apodDataList = mutableListOf<NasaApodDataItem>()

    @Test
    fun validateIfLoadingItemIsDisplayed() {
        apodDataList.add(NasaApodDataItem(
            copyright = "copyright",
            date = "04-05-2024",
            explanation = "explanation",
            hdurl = "hdurl",
            media_type = "media_type",
            service_version = "service_version",
            title = "title",
            url = "url"
        ))
        composeTestRule.setContent {
            CardsItem(
                index = 0,
                apodDataList = apodDataList,
                onClick = {}
            )
        }
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(APOD_CARD_ITEM).isDisplayed()
        composeTestRule.onNodeWithTag(APOD_CARD_ITEM).performClick()
    }
}