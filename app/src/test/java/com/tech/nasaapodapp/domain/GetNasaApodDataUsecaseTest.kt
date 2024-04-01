package com.tech.nasaapodapp.domain

import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.domain.repository.NasaApodRepository
import com.tech.nasaapodapp.domain.usecase.GetNasaApodDataUsecase
import com.tech.nasaapodapp.util.Constant
import com.tech.nasaapodapp.util.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class GetNasaApodDataUsecaseTest {

    private lateinit var getNasaApodDataUsecase: GetNasaApodDataUsecase
    private lateinit var repository: NasaApodRepository
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        repository = mockk()
        getNasaApodDataUsecase = GetNasaApodDataUsecase(repository)
    }

    @Test
    fun `invoke with valid date emits Success with data`() = runTest {
        // Arrange
        val date = "2023-04-01"
        val expectedData = listOf(
            NasaApodDataItem(
                copyright = "NASA",
                date = "2023-04-01",
                explanation = "This is a test explanation",
                hdurl = "https://example.com/image.jpg",
                media_type = "image",
                service_version = "v1",
                title = "Test Image",
                url = "https://example.com/image_small.jpg"
            )
        )
        coEvery { repository.getNasaApod(Constant.API_KEY, date) } returns expectedData

        // Act
        val flow = getNasaApodDataUsecase(date)
        val emissions = flow.toList(mutableListOf())

        // Assert
        coVerify { repository.getNasaApod(Constant.API_KEY, date) }
        assert(emissions.size == 2)
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Success && (emissions[1] as Resource.Success<List<NasaApodDataItem>>).data == expectedData)
    }

    @Test
    fun `invoke with IOException emits Error`() = runTest {
        // Arrange
        val date = "2023-04-01"
        val exception = IOException("Network error")
        coEvery { repository.getNasaApod(Constant.API_KEY, date) } throws exception

        // Act
        val flow = getNasaApodDataUsecase(date)
        val emissions = flow.toList(mutableListOf())

        // Assert
        coVerify { repository.getNasaApod(Constant.API_KEY, date) }
        assert(emissions.size == 2)
        assert(emissions[0] is Resource.Loading)
        assert(emissions[1] is Resource.Error && (emissions[1] as Resource.Error).message == "Couldn't reach server. Check your internet connection.")
    }
}