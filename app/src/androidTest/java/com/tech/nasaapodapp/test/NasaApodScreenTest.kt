package com.tech.nasaapodapp.test

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.nasaapodapp.core.navigation.NavDestination
import com.tech.nasaapodapp.core.ui.NasaActivity
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_LISTING_LOADING
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RequiresApi(Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4::class)
class NasaApodScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<NasaActivity>()

    @Test
    fun validateTopAppBarTitleIsVisibleCorrectly(){
        val title = NavDestination.LIST_TITLE
        composeTestRule.apply{
            onNodeWithText(title).assertIsDisplayed()
        }
    }

    @Test
    fun nasaApodScreen_showsLoadingIndicator() {

        composeTestRule.apply {
            onNodeWithTag(APOD_LISTING_LOADING).assertIsDisplayed()
        }
    }
}