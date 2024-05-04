package com.tech.nasaapodapp.nasa_feature.presentation.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.tech.nasaapodapp.R
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_DETAILS__SCREEN
import com.tech.nasaapodapp.nasa_feature.presentation.theme.NasaApodAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NasaApodItemScreen(model: String, explanation: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .testTag(APOD_DETAILS__SCREEN)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            AsyncImage(
                modifier = Modifier
                    .width(300.dp)
                    .height(250.dp)
                    .align(Alignment.CenterHorizontally),
                model = model,
                contentDescription = stringResource(R.string.label_img_apod_image),
                placeholder = painterResource(
                    id = R.drawable.placeholder
                ),
                error = painterResource(
                    id = R.drawable.placeholder
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = explanation, style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun NasaApodItemScreenPreview() {
    NasaApodAppTheme {
        NasaApodItemScreen(
            model = "model",
            explanation = "explanation",
        )
    }
}