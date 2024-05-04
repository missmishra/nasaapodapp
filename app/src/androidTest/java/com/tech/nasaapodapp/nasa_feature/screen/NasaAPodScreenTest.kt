package com.tech.nasaapodapp.nasa_feature.screen

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_LISTING_LOADING
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_LIST_SUCCESS_SCREEN
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.presentation.screen.NasaApodScreen
import com.tech.nasaapodapp.nasa_feature.presentation.util.NasaApodState
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NasaApodScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateIfLoadingItemIsDisplayed() {
        composeTestRule.setContent {
            NasaApodScreen(
                apodState = MutableStateFlow<NasaApodState>(NasaApodState.Loading).collectAsState(),
                onClick = {}
            )
        }
        composeTestRule.onNodeWithText(APOD_LISTING_LOADING).isDisplayed()
    }

    @Test
    fun validateIfSuccessItemIsDisplayed() {
        composeTestRule.setContent {
            NasaApodScreen(
                apodState = MutableStateFlow<NasaApodState>(NasaApodState.Success(getMockList)).collectAsState(),
                onClick = {}
            )
        }
        composeTestRule.onNodeWithText(APOD_LIST_SUCCESS_SCREEN).isDisplayed()
    }

    private val getMockList: List<NasaApodDataItem> = mutableListOf<NasaApodDataItem>().apply {
        NasaApodDataItem(
            copyright = "copyright",
            date = "04-05-2024",
            explanation = "explanation",
            hdurl = "hdurl",
            media_type = "media_type",
            service_version = "service_version",
            title = "title",
            url = "url"
        )
    }
}