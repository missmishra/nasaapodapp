package com.tech.nasaapodapp.test

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tech.nasaapodapp.presentation.MainActivity
import com.tech.nasaapodapp.presentation.component.NasaApodScreen
import com.tech.nasaapodapp.presentation.component.NasaApodState
import com.tech.nasaapodapp.presentation.component.NasaApodViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RequiresApi(Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4::class)
class NasaApodScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun validateTopAppBarTitleIsVisibleCorrectly(){
        composeTestRule.apply{
            onNodeWithText("Nasa App").assertIsDisplayed()
        }
    }

    @Test
    fun nasaApodScreen_showsLoadingIndicator() {
        composeTestRule.apply {
            onNodeWithTag("progress").assertIsDisplayed()
        }
    }
}