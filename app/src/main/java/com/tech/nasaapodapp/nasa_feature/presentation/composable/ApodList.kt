package com.tech.nasaapodapp.nasa_feature.presentation.composable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tech.nasaapodapp.R
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ApodList(
    apodList: List<NasaApodDataItem>, modifier: Modifier,
    onClick: (NasaApodDataItem) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(id = R.string.label_history),
            color = MaterialTheme.colorScheme.inversePrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            items(apodList.size) { index ->
                CardsItem(
                    index = index,
                    apodDataList = apodList,
                    onClick = { item -> onClick(item) }
                )
            }
        }

    }
}