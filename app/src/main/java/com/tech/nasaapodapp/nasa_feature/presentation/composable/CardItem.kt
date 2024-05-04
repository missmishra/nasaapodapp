package com.tech.nasaapodapp.nasa_feature.presentation.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tech.nasaapodapp.R
import com.tech.nasaapodapp.core.util.Constant.TestTags.APOD_CARD_ITEM
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import timber.log.Timber

@Composable
fun CardsItem(
    index: Int,
    apodDataList: List<NasaApodDataItem>,
    onClick: (NasaApodDataItem) -> Unit
) {
    val apodData = apodDataList[index]
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(350.dp)
            .padding(20.dp)
            .testTag(APOD_CARD_ITEM)
            .background(MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        onClick = {
            Timber.e("LISTING SCREEN onClick: $index")
            onClick(apodData)
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = apodData.title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))

            AsyncImage(
                modifier = Modifier
                    .width(200.dp)
                    .height(150.dp),
                model = apodData.hdurl,
                contentDescription = "APOD Image",
                placeholder = painterResource(
                    id = R.drawable.placeholder
                ),
                error = painterResource(
                    id = R.drawable.placeholder
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = apodData.explanation,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}