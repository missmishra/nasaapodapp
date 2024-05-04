package com.tech.nasaapodapp.nasa_feature.presentation.viewmodel

import com.tech.nasaapodapp.core.util.Resource
import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem
import com.tech.nasaapodapp.nasa_feature.domain.usecase.GetNasaApodDataUsecase
import com.tech.nasaapodapp.nasa_feature.presentation.util.NasaApodState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.test.assertEquals

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NasaApodViewModelTest {

    @MockK
    lateinit var mockGetNasaApodDataUsecase: GetNasaApodDataUsecase
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: NasaApodViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        sut = NasaApodViewModel(
            getNasaApodDataUsecase = mockGetNasaApodDataUsecase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `validate state when getApodData returns success`() = runBlockingTest {
        coEvery { mockGetNasaApodDataUsecase.invoke(getMockDate()) } coAnswers {
            flow {
                emit(Resource.Success(getMockList))
            }
        }
        sut.getApodData(getMockDate())
        assertEquals(NasaApodState.Success(getMockList), sut.state.value)
    }

    @Test
    fun `validate state when getApodData returns error`() = runBlockingTest {
        coEvery { mockGetNasaApodDataUsecase.invoke(getMockDate()) } coAnswers {
            flow {
                emit(Resource.Error("Error occured"))
            }
        }
        sut.getApodData(getMockDate())
        assertEquals(NasaApodState.Error("Error occured"), sut.state.value)
    }

    @Test
    fun `validate state when getApodData returns loading`() = runBlockingTest {
        coEvery { mockGetNasaApodDataUsecase.invoke(getMockDate()) } coAnswers {
            flow {
                emit(Resource.Loading())
            }
        }
        sut.getApodData(getMockDate())
        assertEquals(NasaApodState.Loading, sut.state.value)
    }


    private fun getMockDate(): String {
        val currentDateTime = LocalDateTime.now().minus(30, ChronoUnit.DAYS)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return currentDateTime.format(formatter)
    }

    private val getMockList: List<NasaApodDataItem> = mutableListOf<NasaApodDataItem>().apply {
        NasaApodDataItem(
            copyright = "copyright",
            date = getMockDate(),
            explanation = "explanation",
            hdurl = "hdurl",
            media_type = "media_type",
            service_version = "service_version",
            title = "title",
            url = "url"
        )
    }
}