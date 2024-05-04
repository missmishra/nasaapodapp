package com.tech.nasaapodapp.nasa_feature.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tech.nasaapodapp.R
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_LISTING_LOADING
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_LIST_SUCCESS_SCREEN
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.presentation.composable.ApodList
import com.tech.nasaapodapp.nasa_feature.presentation.composable.ErrorItem
import com.tech.nasaapodapp.nasa_feature.presentation.composable.LoadingItem
import com.tech.nasaapodapp.nasa_feature.presentation.theme.NasaApodAppTheme
import com.tech.nasaapodapp.nasa_feature.presentation.util.NasaApodState
import kotlinx.coroutines.flow.MutableStateFlow


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NasaApodScreen(
    apodState: State<NasaApodState>,
    onClick: (NasaApodDataItem) -> Unit,
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        when (apodState.value) {
            is NasaApodState.Error -> {
                ErrorItem(
                    errorMessage = (apodState.value as NasaApodState.Error).message,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                        .testTag(stringResource(R.string.label_test_tag_error))
                )
            }

            NasaApodState.Loading -> {
                LoadingItem(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                        .testTag(APOD_LISTING_LOADING)
                )
            }

            is NasaApodState.Success -> {
                (apodState.value as NasaApodState.Success).nasaApodData?.let { apoodList ->
                    ApodList(
                        apodList = apoodList,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .testTag(APOD_LIST_SUCCESS_SCREEN),
                        onClick = { item -> onClick(item) }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun NasaApodScreenPreview() {
    NasaApodAppTheme {
        NasaApodScreen(
            apodState = MutableStateFlow<NasaApodState>(NasaApodState.Success(getTestList)).collectAsState(),
            onClick = {}
        )
    }
}


private val getTestList: List<NasaApodDataItem> = mutableListOf<NasaApodDataItem>().apply {
    NasaApodDataItem(
        copyright = "copyright",
        date = "",
        explanation = "explanation",
        hdurl = "hdurl",
        media_type = "media_type",
        service_version = "service_version",
        title = "title",
        url = "url"
    )
}