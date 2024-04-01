package com.tech.nasaapodapp.data


import com.tech.nasaapodapp.data.remote.NasaApodAPI
import com.tech.nasaapodapp.data.remote.dto.NasaApodDataItem
import com.tech.nasaapodapp.data.repository.NasaApodRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NasaApodRepositoryImplTest {

    @MockK
    private lateinit var nasaApodApi: NasaApodAPI
    private lateinit var repository: NasaApodRepositoryImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        repository = NasaApodRepositoryImpl(nasaApodApi)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `getNasaApod with valid API key and date returns expected data`() = runBlockingTest {
        // Arrange
        val apiKey = "valid_api_key"
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
        coEvery { nasaApodApi.getApod(apiKey, date) } returns expectedData

        // Act
        val result = repository.getNasaApod(apiKey, date)

        // Assert
        coVerify { nasaApodApi.getApod(apiKey, date) }
        assertEquals(expectedData, result)
    }

    @Test
    fun `getNasaApod with null date returns data for today`() = runBlockingTest {
        // Arrange
        val apiKey = "valid_api_key"
        val date = null
        val expectedData = listOf(
            NasaApodDataItem(
                copyright = "NASA",
                date = "2023-04-01", // today's date
                explanation = "This is a test explanation",
                hdurl = "https://example.com/image.jpg",
                media_type = "image",
                service_version = "v1",
                title = "Test Image",
                url = "https://example.com/image_small.jpg"
            )
        )
        coEvery { nasaApodApi.getApod(apiKey, null) } returns expectedData

        // Act
        val result = repository.getNasaApod(apiKey, date)

        // Assert
        coVerify { nasaApodApi.getApod(apiKey, null) }
        assertEquals(expectedData, result)
    }

    @Test
    fun `getNasaApod with invalid API key throws exception`() = runBlockingTest {
        // Arrange
        val apiKey = "invalid_api_key"
        val date = "2023-04-01"
        coEvery { nasaApodApi.getApod(apiKey, date) } throws Exception("Invalid API key")

        // Act & Assert
        assertFailsWith<Exception> {
            repository.getNasaApod(apiKey, date)
        }
    }

    @Test
    fun `getNasaApod with empty response returns empty list`() = runBlockingTest {
        val apiKey = "valid_api_key"
        val date = "2023-04-01"
        coEvery { nasaApodApi.getApod(apiKey, date) } returns emptyList()

        // Act
        val result = repository.getNasaApod(apiKey, date)

        // Assert
        coVerify { nasaApodApi.getApod(apiKey, date) }
        assertTrue(result.isEmpty())
    }
}
