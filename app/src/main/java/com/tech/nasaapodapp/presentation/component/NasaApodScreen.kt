package com.tech.nasaapodapp.presentation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.tech.nasaapodapp.R
import com.tech.nasaapodapp.data.remote.dto.NasaApodData
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.presentation.Greeting
import com.tech.nasaapodapp.presentation.theme.NasaApodAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NasaApodScreen(viewModel: NasaApodViewModel = hiltViewModel()) {
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface)
    ) {
        val apodState = viewModel.state.value
        apodState.apod?.let { apodResult ->
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp,
                            color = MaterialTheme.colorScheme.onError
                        )
                    ) {
                        append("APOD: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            color = Color.Gray
                        )
                    ) {
                        append("Astronomy Picture of day \n \n \n \n \n \n \n \n \n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Normal,
                            fontSize = 22.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("30 Days of APOD History")
                    }
                },
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(10.dp, 40.dp, 0.dp, 20.dp)
            )
            LazyRow(Modifier.align(Alignment.Center)) {
                items(apodResult.size) { index ->
                    CardsItem(index = index, apodDataList = apodResult)
                }
            }
        }
        if (apodState.error.isNotBlank()) {
            Text(
                text = apodState.error,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (apodState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }


}

@Composable
fun CardsItem(index: Int, apodDataList: List<NasaApodDataItem>) {
    val apodData = apodDataList[index]
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(350.dp)
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = apodData.title, fontSize = 17.sp,
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
